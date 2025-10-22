import java.security.*;
import java.util.Base64;

public class DigitalSignatureDemo {

    private static final String ALGORITHM = "SHA256withRSA";
    private static final int KEY_SIZE = 2048;

    public static void main(String[] args) {
        try {
            KeyPair keyPair = generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            String originalMessage = "This is a secret message to be digitally signed.";
            System.out.println("Original Message: " + originalMessage);

            byte[] signatureBytes = signMessage(originalMessage.getBytes(), privateKey);
            String signatureBase64 = Base64.getEncoder().encodeToString(signatureBytes);
            System.out.println("Digital Signature (Base64): " + signatureBase64);

            boolean verified = verifySignature(originalMessage.getBytes(), signatureBytes, publicKey);
            System.out.println("Signature Verified: " + verified);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(KEY_SIZE);
        return keyPairGenerator.generateKeyPair();
    }

    private static byte[] signMessage(byte[] data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance(ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    private static boolean verifySignature(byte[] data, byte[] digitalSignature, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance(ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(digitalSignature);
    }
}
