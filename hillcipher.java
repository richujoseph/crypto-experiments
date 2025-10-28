import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your Password: ");
        String password = sc.nextLine().toUpperCase().replaceAll("\\s","");
        if(password.length()%2!=0){
            password += "X";
        }
       int [][] K= new int[2][2];
        System.out.println("Enter the key matrix: ");
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                K[i][j]=sc.nextInt();
            }
        }
        StringBuilder sb= new StringBuilder();
        for(int i=0;i<password.length();i+=2){
            int p1=password.charAt(i)-65,p2=password.charAt(i+1)-65;
            int c1=mod26(K[0][0]*p1+K[0][1]*p2);
            int c2=mod26(K[1][0]*p1+K[1][1]*p2);
            sb.append((char)(c1+65)).append((char)(c2+65));
        }
        String cipher=sb.toString();
        System.out.println(cipher);

        int det = mod26(K[0][0]*K[1][1]-K[1][0]*K[0][1]);
        int invDet = mulInverse(det,26);
        if(invDet==-1){
            System.out.println("Invalid Input");
            return;
        }
        int a =K[0][0],b=K[0][1],c=K[1][0],d=K[1][1];
        int[][] invK = {
                {mod26(d*invDet), mod26(-b*invDet)},
                {mod26(-c*invDet), mod26(a*invDet)},
        };
        StringBuilder sb1= new StringBuilder();
        for (int i=0;i<cipher.length();i+=2){
            int c1=cipher.charAt(i)-65,c2=cipher.charAt(i+1)-65;
            int p1=mod26(invK[0][0]*c1+invK[0][1]*c2);
            int p2=mod26(invK[1][0]*c1+invK[1][1]*c2);
            sb1.append((char) (p1+65)).append((char)(p2+65));
        }
        System.out.println(sb1.toString());
        }
    static int mod26(int x){
        return ((x %26)+26)%26;
    }
    static int mulInverse(int a,int b){
        a = mod26(a);
        for(int k=0;k<b;k++){
            if((a * k)%b==1)
                return k;
        }
        return -1;
    }
}
