package LoopJava.GreatestCommonFactor;
import java.util.Scanner;
public class GreatestCommonFactor {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a:");
        int a = input.nextInt();
        System.out.println("Enter b:");
        int b = input.nextInt();
        input.close();
        a = Math.abs(a);
        b = Math.abs(b);

        if (a == 0 || b == 0) {
            System.out.println("No greatest common facotr");
            return;
        }

        while (a != b) {
            if (a > b)
                a -= b;
            else
                b -= a;
        }
        System.out.println("Greatest common factor: " + a);

    }
}
