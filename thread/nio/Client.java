import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        int port = (args.length > 0) ? Integer.valueOf(args[0]) : 8000;

        Socket client = null;
        PrintWriter writer = null;
        BufferedReader reader = null;

        for (int i = 0; i < 10; ++i) {
            try {
                client = new Socket();
                client.connect(new InetSocketAddress(InetAddress.getLocalHost(), port));
                writer = new PrintWriter(client.getOutputStream(), true);
                writer.println("Hello");
                writer.flush();

                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                System.out.println("from server: " + reader.readLine());
            } catch (IOException e) {
                e.printStackTrace(System.err);
            } finally {
                if (writer != null) {
                    writer.close();
                }
                if (reader != null) {
                    reader.close();
                }
                if (client != null) {
                    client.close();
                }
            }
        }
    }
}
