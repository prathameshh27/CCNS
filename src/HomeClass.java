import java.util.Scanner;
public class HomeClass {

    public static void main(String args[]){

        int option;
        Scanner input = new Scanner(System.in);
        System.out.println("1. Caesar Cipher \t 2. Playfair Cipher");
        option=input.nextInt();

        switch (option){ //option
            case 1:
                CaesarCipher.init();
                break;

            case 2:
                PlayfairCipher.init();
                break;

            default:
                System.out.println("Wrong Choice");
        }
    }
}
