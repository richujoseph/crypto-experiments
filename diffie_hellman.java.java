import java.util.Random;

public class dh {

    public static void main(String[] args) {
        int p = 23;
        int g = 5;

        System.out.println("Publicly shared values:");
        System.out.println("Prime (p): " + p);
        System.out.println("Primitive root (g): " + g);

        Random rand = new Random();

        int a = rand.nextInt(p - 2) + 1;
        int A = (int) (Math.pow(g, a) % p);

        int b = rand.nextInt(p - 2) + 1;
        int B = (int) (Math.pow(g, b) % p);

        System.out.println("\nPrivate keys (kept secret):");
        System.out.println("Alice's private key (a): " + a);
        System.out.println("Bob's private key (b): " + b);

        System.out.println("\nPublic values exchanged:");
        System.out.println("Alice sends: " + A);
        System.out.println("Bob sends: " + B);

        int secretAlice = (int) (Math.pow(B, a) % p);
        int secretBob = (int) (Math.pow(A, b) % p);

        System.out.println("\nShared secrets computed:");
        System.out.println("Alice's secret: " + secretAlice);
        System.out.println("Bob's secret: " + secretBob);

        if (secretAlice == secretBob) {
            System.out.println("\nShared secret key established!");
        } else {
            System.out.println("\nSomething went wrong!");
        }
    }
}