import java.util.Scanner;

public class PlayfairCipher {
    static char keepTrack[]={'0','0','0','0','0','0','0','0','0','0','0','0','0',
            '0','0','0','0','0','0','0','0','0','0','0','0','0'};

    static char keyMatrix[][]=new char[5][5];
    static int option;


    public static void playfairCipher(String message, String key){

        int index, counter=0;
        char alphaPointer='a';

        //populate keyMatrix
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++)
            {

                //populate with the key
                if(counter<key.length())
                {
                    index =(int)key.charAt(counter)-(int)'a';
                    if(keepTrack[index]=='0'){
                        keepTrack[index]=key.charAt(counter);
                        keyMatrix[i][j]=key.charAt(counter);
                    }
                    else j--;
                    //for debugging purpose
                    //System.out.println(key.charAt(counter)+"\t"+index+"\t"+"\t"+i+" "+j);
                    counter++;
                }

                //populate with remaining alphabets
                else{
                    index =(int)alphaPointer -(int)'a';
                    if(keepTrack[index]=='0'){
                        keepTrack[index]=alphaPointer;
                        keyMatrix[i][j]=alphaPointer;
                    }
                    else j--;
                    //for debugging purpose
                    //System.out.println(alphaPointer+"\t"+index+"\t"+"\t"+i+" "+j);

                    alphaPointer++;
                    if((alphaPointer=='i' && keepTrack[9]=='j')||alphaPointer=='j') alphaPointer++; //index of j is 9
                }
            }
        }

        //prints keyMatrix
        for(int i=0; i<5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(keyMatrix[i][j]+"  ");
            }System.out.println();
        }
    }


    public static void init(){
        String message, key;
        Scanner input = new Scanner(System.in);

        //System.out.println("Press 1 for Encryption & 2 for Decryption");
        //option = input.nextInt();
        //System.out.println("Enter message");
        //message = input.next();
        message="";
        System.out.println("Enter key");
        key = input.next();
        playfairCipher(message, key);

    }


}
