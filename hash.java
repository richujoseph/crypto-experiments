import java.util.Scanner;

public class hash {
    public static byte xorHash(byte[] data) {
        byte result = 0;
        for (byte b : data) {
            result ^= b;
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string to hash with XOR: ");
        String input = scanner.nextLine();

        byte[] bytes = input.getBytes();

        byte hash = xorHash(bytes);
       
        System.out.println("XOR Hash (decimal): " + (hash & 0xFF));
        System.out.println("XOR Hash (hex): 0x" + Integer.toHexString(hash & 0xFF).toUpperCase());

        scanner.close();
    }
}