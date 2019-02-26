import java.util.Scanner;

public class PlayfairCipher {
    //global data
    static int stateTracker[]={0,0,0,0,0,0,0,0,0,0,0,0,0,
                             0,0,0,0,0,0,0,0,0,0,0,0,0};
    static char keyMatrix[][]=new char[5][5];

    static int option;

    //displays populated matrix
    public static void displayMatrix(){
        System.out.println();
        for(int i=0; i<5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(keyMatrix[i][j]+"  ");
            }System.out.println();
        }
    }

    public static void displayStateTracker(){
        System.out.println();
        for(int i=0; i<stateTracker.length; i++)
                System.out.print(stateTracker[i]+"  ");
    }

    //generates step 1 matrix using entered key
    public static void genrateMatrix(String key){

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
                    if(stateTracker[index]==0){
                        //stateTracker[index]=key.charAt(counter);
                        stateTracker[index]=(i+1)*10+(j+1);
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
                    if(stateTracker[index]==0){
                        //stateTracker[index]=alphaPointer;
                        stateTracker[index]=(i+1)*10+(j+1);
                        keyMatrix[i][j]=alphaPointer;
                    }
                    else j--;
                    //for debugging purpose
                    //System.out.println(alphaPointer+"\t"+index+"\t"+"\t"+i+" "+j);

                    alphaPointer++;
                    if((alphaPointer=='i' && stateTracker[9]=='j')||alphaPointer=='j') alphaPointer++; //index of j is 9
                }
            }
        }
    }

    public static void playfairCipher(String message)
    {
        int char1Index, char2Index, rowChar1, colChar1, rowChar2, colChar2, temp;
        char alteredChar1, alteredChar2;

        for(int i=0; i<message.length(); i=i+2)
        {
            //determines where the char is located in matrix
                temp=(int)message.charAt(i)-(int)'a';
                char1Index=stateTracker[temp];
                rowChar1=char1Index/10-1;
                colChar1=char1Index%10-1;

                temp=(int)'x'-(int)'a';

                if(message.charAt(i)!= message.charAt(i+1))
                    temp=(int)message.charAt(i+1)-(int)'a';

                else
                    i--;

                char2Index=stateTracker[temp];
                rowChar2=char2Index/10-1;
                colChar2=char2Index%10-1;


            //chars with same rows/cols obey different rules
            if(rowChar1==rowChar2)
            {
                colChar1=colChar1++ % 4;
                colChar2=colChar2++ % 4;
            }

            else if(colChar1==colChar2)
            {
                rowChar1=rowChar1++ % 4;
                rowChar2=rowChar2++ % 4;
            }

            //char with diff rows and cols
            else
            {
                temp=colChar1;
                colChar1=colChar2;
                colChar2=temp;
            }

            System.out.print(keyMatrix[rowChar1][colChar1]); //alters char1
            System.out.print(keyMatrix[rowChar2][colChar2]); //alters char2

        }
    }


    public static void init(){
        String message, key;
        Scanner input = new Scanner(System.in);

        //System.out.println("Press 1 for Encryption & 2 for Decryption");
        //option = input.nextInt();
        System.out.println("Enter message");
        message = input.nextLine();

        System.out.println("Enter key");
        key = input.next();
        //generates step1 matrix
        genrateMatrix(key);
        //prints keyMatrix
        displayMatrix();
        displayStateTracker();
        //performs encryption / decryption
        playfairCipher(message);

    }
}
