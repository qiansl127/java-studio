import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioServer {
    private Selector selector;
    private ExecutorService tp = Executors.newCachedThreadPool();

    private void startServer(int port) throws Exception {
        selector = SelectorProvider.provider().openSelector();

        // get the channel instance
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        // bind channel to the specified port
        InetSocketAddress isa = new InetSocketAddress(InetAddress.getLocalHost(), port);
        ssc.socket().bind(isa);

        // register channel to selector, interested in ACCEPT time
        // the return value SelectionKey instance stands for the relationship between selector and channel
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Set readyKeys = selector.selectedKeys();
            Iterator i = readyKeys.iterator();
            while (i.hasNext()) {
                SelectionKey sk = (SelectionKey) i.next();
                i.remove();

                if (sk.isAcceptable()) {
                    doAccept(sk);
                } else if (sk.isValid() && sk.isReadable()) {
                    doRead(sk);
                } else if (sk.isValid() && sk.isWritable()) {
                    doWrite(sk);
                }
            }
        }
    }

    private void doAccept(SelectionKey sk) {
        ServerSocketChannel server = (ServerSocketChannel) sk.channel();
        SocketChannel clientChannel;

        try {
            clientChannel = server.accept();
            clientChannel.configureBlocking(false);

            // register this channel for reading
            SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ);
            // Allocate an EchoClient instance and attach it to this selection key
            EchoClient echoClient = new EchoClient();
            clientKey.attach(echoClient);

            InetAddress clientAddress = clientChannel.socket().getInetAddress();
            System.out.println("Accepted connection from " + clientAddress.getHostAddress() + ".");
        } catch (Exception e) {
            System.err.println("Failed to accept new client");
            e.printStackTrace(System.err);
        }
    }

    private void doRead(SelectionKey sk) {
        SocketChannel channel = (SocketChannel) sk.channel();
        ByteBuffer bb = ByteBuffer.allocate(8192);
        int len;

        try {
            len = channel.read(bb);
            if (len < 0) {
//                disconnect(sk);
                return;
            }
        } catch (Exception e) {
            System.err.println("Failed to read from client");
            e.printStackTrace(System.err);
//            disconnect(sk);
            return;
        }
        bb.flip();
        tp.execute(new HandleMsg(sk, bb));
    }

    private void doWrite(SelectionKey sk) {
        SocketChannel channel = (SocketChannel) sk.channel();
        EchoClient echoClient = (EchoClient) sk.attachment();
        LinkedList<ByteBuffer> outq = echoClient.getOutputQueue();

        ByteBuffer bb = outq.getLast();
        try {
            int len = channel.write(bb);
            if (len == -1) {
//                disconnect(sk);
                return;
            }
            if (bb.remaining() == 0) {
                // completely written, remove it
                outq.removeLast();
            }
        } catch (Exception e) {
            System.err.println("Failed to write to client");
            e.printStackTrace(System.err);
//            disconnect(sk);
        }

        if (outq.size() == 0) {
            sk.interestOps(SelectionKey.OP_READ);
        }
    }

    class HandleMsg implements Runnable {
        SelectionKey sk;
        ByteBuffer bb;

        HandleMsg(SelectionKey sk, ByteBuffer bb) {
            this.sk = sk;
            this.bb = bb;
        }

        @Override
        public void run() {
            EchoClient echoClient = (EchoClient) sk.attachment();
            echoClient.enqueue(bb);
            sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            // force selector return immediately
            selector.wakeup();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = (args.length > 0) ? Integer.valueOf(args[0]) : 8000;
        (new NioServer()).startServer(port);
    }
}

class EchoClient {
    private LinkedList<ByteBuffer> outq;

    EchoClient() {
        outq = new LinkedList<>();
    }

    LinkedList<ByteBuffer> getOutputQueue() {
        return outq;
    }

    void enqueue(ByteBuffer bb) {
        outq.addFirst(bb);
    }
}

