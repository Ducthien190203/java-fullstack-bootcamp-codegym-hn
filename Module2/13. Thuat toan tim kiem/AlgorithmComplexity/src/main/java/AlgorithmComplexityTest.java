import java.util.Scanner;

public class AlgorithmComplexityTest {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        System.out.println("Enter a string:");
        String input=scanner.nextLine();

        int[] frequentChar=new int[255];
        for (int i=0;i<input.length();i++){
            int ascii= (int) input.charAt(i);
            frequentChar[ascii]++;
        }

        int max=0;
        char character=(char)255;
        for(int j=0;j<255;j++){
            if(frequentChar[j]>max){
                max=frequentChar[j];
                character=(char)j;
            }
        }
        System.out.println("The most appearing letter is '" + character + "' with a frequency of " + max + " times");    }
}
