import java.util.Scanner;

public class PlayfairCipher {
    //global data
    static int stateTracker[]={0,0,0,0,0,0,0,0,0,0,0,0,0,
                             0,0,0,0,0,0,0,0,0,0,0,0,0};
    static char keyMatrix[][]=new char[5][5];

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
        System.out.println();
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

    public static int validateIndex(int index) {
        if(index<4)
            return index+1;
        else return 0;
    }

    public static void playfairCipher(String message) {
        int char1Index, char2Index, rowIndexChar1, colIndexChar1, rowIndexChar2, colIndexChar2, temp;

        for(int i=0; i<message.length(); i=i+2) {
            //determines where the char is located in matrix
                temp=(int)message.charAt(i)-(int)'a';
                char1Index=stateTracker[temp];          //for char 1
                rowIndexChar1=char1Index/10-1;
                colIndexChar1=char1Index%10-1;

                temp=(int)'x'-(int)'a';

                try{
                    if(message.charAt(i)!= message.charAt(i+1))
                        temp=(int)message.charAt(i+1)-(int)'a';

                    else
                        i--;
                }

                catch (StringIndexOutOfBoundsException e){ }

                char2Index=stateTracker[temp];          //for char 2
                rowIndexChar2=char2Index/10-1;
                colIndexChar2=char2Index%10-1;


            //chars with same rows/cols obey different rules
            if(rowIndexChar1==rowIndexChar2) {
                colIndexChar1=validateIndex(colIndexChar1);
                colIndexChar2=validateIndex(colIndexChar2);
                System.out.println("same row: ");           //for debugging purpose
            }


            else if(colIndexChar1==colIndexChar2) {
                rowIndexChar1=validateIndex(rowIndexChar1);
                rowIndexChar2=validateIndex(rowIndexChar2);
                System.out.println("same col: ");           //for debugging purpose
            }

            //char with diff rows and cols
            else {
                temp=colIndexChar1;
                colIndexChar1=colIndexChar2;
                colIndexChar2=temp;
                System.out.println("diff row n col: ");     //for debugging purpose
            }

            System.out.print(keyMatrix[rowIndexChar1][colIndexChar1]); //alters char1
            System.out.println(keyMatrix[rowIndexChar2][colIndexChar2]); //alters char2
        }
    }

    public static void init(){
        String message, key;
        Scanner input = new Scanner(System.in);

        System.out.println("Enter key");
        key = input.nextLine();

        System.out.println("Enter message");
        message = input.nextLine();

        genrateMatrix(key);         //generates step1 matrix

        //for debugging purpose
        displayMatrix();            //prints keyMatrix
        displayStateTracker();      //displays pos of each letter in mat

        playfairCipher(message);    //performs encryption
    }
}
