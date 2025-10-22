,package hill_cipher;
import java.util.*;

public class hill {
	public static int determinant(int matrix[][]) {
		return matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
	}
	
	public static int modInverse(int a) {
		a = a % 26;
		for(int i=1;i<26;i++){
			if ((a*i)%26 == 1){
				return i;
			}
		}
		return -1;
	}
	
	public static int[][] adjointMatrix(int matrix[][]) {
		int[][] adjoint = new int[2][2];
		adjoint[0][0]=matrix[1][1];
		adjoint[1][1]=matrix[0][0];
		adjoint[0][1]=-matrix[0][1];
		adjoint[1][0]=-matrix[1][0];
		return adjoint;
	}
	
	public static int[][] inverseMatrix(int matrix[][]) {
		int det = determinant(matrix);
		int detInverse = modInverse(det);
		int[][] inverse = new int[2][2];
		if (detInverse == -1){
			System.out.println("Inverse doesn't exist\n");
		}
		int[][] adjoint = adjointMatrix(matrix);
		
		for (int i=0;i<2;i++){
			for(int j=0;j<2;j++){
				inverse[i][j] = (adjoint[i][j] * detInverse) %26;
				if(inverse[i][j] < 0){
					inverse[i][j] += 26;
				}
			}
		}
		return inverse;
	}
	
	public static String encrypt(String plaintext, int key[][]) {
		StringBuilder ciphertext = new StringBuilder();
		int len = plaintext.length();
		for (int i=0; i<len; i+=2) {
			int p1 = plaintext.charAt(i) - 'A';
			if (p1<0) {
					p1 = (p1*-1);
			}
			int p2 = plaintext.charAt(i+1) - 'A';
			if (p2<0) {
				p2 = (p2*-1);
			}
			
			int c1 = (p1*key[0][0] + p2*key[0][1]) % 26;
			int c2 = (p1*key[1][0] + p2*key[1][1]) % 26;
			
			ciphertext.append((char)(c1 + 'A'));
			ciphertext.append((char)(c2 + 'A'));
		}
		return ciphertext.toString();
		
	}
	
	public static String decrypt(String ciphertext, int key[][]) {
		StringBuilder decryptedText = new StringBuilder();
		int len = ciphertext.length();
		int inverseKey[][] = inverseMatrix(key);
		for (int i=0; i<len; i+=2) {
			int c1 = ciphertext.charAt(i) - 'A';
			int c2 = ciphertext.charAt(i+1) - 'A';
			
			int p1 = (c1*inverseKey[0][0] + c2*inverseKey[0][1]) % 26;
			int p2 = (c1*inverseKey[1][0] + c2*inverseKey[1][1]) % 26;
			
			decryptedText.append((char)(p1 + 'A'));
			decryptedText.append((char)(p2 + 'A'));
		}
		return decryptedText.toString();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] key = {{3,3}, {2,5}};
		System.out.print("Enter plaintext: ");
		String plaintext = sc.nextLine().toUpperCase();
		if (plaintext.length() % 2 != 0) {
			plaintext += "X";
		}
		
		String encryptedText = encrypt(plaintext, key);
		System.out.println("Encrypted Text: "+encryptedText);
		String decryptedText = decrypt(encryptedText, key);
		System.out.println("\nDecrypted Text: " + decryptedText);
	}
}