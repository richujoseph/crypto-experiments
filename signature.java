import java.security.*;
import java.util.Base64;

public class SimpleDigitalSignature {
    public static void main(String[] args) {
        try {
            
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair pair = keyGen.generateKeyPair();

            PublicKey pubKey = pair.getPublic();
            PrivateKey privKey = pair.getPrivate();

            
            String message = "Hello, this is a digital signature demo!";
            System.out.println("Original Message: " + message);

           
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(privKey);
            sign.update(message.getBytes());
            byte[] signature = sign.sign();

            String signBase64 = Base64.getEncoder().encodeToString(signature);
            System.out.println("Signature: " + signBase64);

            
            Signature verifySign = Signature.getInstance("SHA256withRSA");
            verifySign.initVerify(pubKey);
            verifySign.update(message.getBytes());
            boolean result = verifySign.verify(signature);

            System.out.println("Signature Verified: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
