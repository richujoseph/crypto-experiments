import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import java.util.Base64;
import java.util.Scanner;

public class RSA {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            KeyPair keyPair = generator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            System.out.print("Enter the message to encrypt: ");
            String message = scanner.nextLine();

            Cipher encryptCipher = Cipher.getInstance("RSA");
            encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedMessageBytes = encryptCipher.doFinal(message.getBytes());
            String encryptedText = Base64.getEncoder().encodeToString(encryptedMessageBytes);

            System.out.println("Original Message: " + message);
            System.out.println("Encrypted Text: " + encryptedText);

            Cipher decryptCipher = Cipher.getInstance("RSA");
            decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decodedMessageBytes = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedMessageBytes = decryptCipher.doFinal(decodedMessageBytes);
            String decryptedText = new String(decryptedMessageBytes);
            
            System.out.println("Decrypted Text: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}