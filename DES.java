import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Scanner;

public class DES {

    public static void main(String[] args) {

        try {

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter text: ");

            String originalText = sc.nextLine();

            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            SecretKey secretKey = keyGen.generateKey();

            Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            desCipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] textBytes = originalText.getBytes();
            byte[] encryptedBytes = desCipher.doFinal(textBytes);

            String encryptedBase64 =
                    Base64.getEncoder().encodeToString(encryptedBytes);

            System.out.println("Encrypted Text (Base64): " + encryptedBase64);

            desCipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = desCipher.doFinal(encryptedBytes);

            String decryptedText = new String(decryptedBytes);
            System.out.println("Decrypted Text: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}