public class Main {

    public static void main(String[] args) {
        Student frank = new Student("frank", 17, null);
        Student raven = new Student(null, 17, Gender.FEMALE);

        print(toBinary(frank.hashCode()));
        print(toBinary(raven.hashCode()));

        print(frank.equals(null));   // false
        print(frank.equals(frank));  // true
        print(frank.equals(raven));  // false
        print(raven.equals(frank));  // false

        print(frank);
        print(raven);
    }

    private static void testToBinary() {
        print(toBinary(-2));
        print(toBinary(2));
        print(toBinary(Integer.MAX_VALUE));
        print(toBinary(Integer.MIN_VALUE));
    }

    private static String toBinary(int num) {
        if (num == Integer.MIN_VALUE) return "10000000000000000000000000000000";
        if (num < 0) {
            String temp = toBinary(0 - num);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < temp.length(); ++i) {
                sb.append(temp.charAt(i) == '0' ? '1' : '0');
            }
            for (int i = sb.length() - 1; i >= 0; --i) {
                if (sb.charAt(i) == '0') {
                    sb.setCharAt(i, '1');
                    break;
                }
                sb.setCharAt(i, '0');
            }
            return sb.toString();
        }
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.insert(0, (num % 2 > 0) ? '1' : '0');
            num = num / 2;
        }
        int length = sb.length();
        for (int i = 0; i < 32 - length; ++i) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }

    private static <T> void print(T s) {
        System.out.println(s);
    }

}