import java.util.Scanner;

public class CaesarCipher {

    public static int option;

    public static void caesarCipher(String message, int key){

        int index, alteredIndex, mod;
        //for debugging purpose
        System.out.println("index\tchar\talter\tmod\tchar");

        for (int i=0; i<message.length(); i++) {
            index =(int)message.charAt(i)-(int)'a';
            mod=(index+key)%26;
            if(mod<0) mod=26+mod;
            alteredIndex =(int)'a' + mod;
            //System.out.print((char)alteredIndex);

            //for debugging purpose
            System.out.println(index+"\t"+message.charAt(i)+"\t"+alteredIndex+"\t"+mod+"\t"+(char)alteredIndex);

        }
    }

    public static void init(){
        String message;
        int key;
        Scanner input = new Scanner(System.in);

        System.out.println("Press 1 for Encryption & 2 for Decryption");
        option = input.nextInt();
        System.out.println("Enter message");
        message = input.next();
        System.out.println("Enter key");
        key = input.nextInt();

        switch(option){
            case 1:
                caesarCipher(message, key);
                break;

            case 2:
                caesarCipher(message, -key);
                break;

            default:
                System.out.println("Wrong choice");
        }
    }

}
