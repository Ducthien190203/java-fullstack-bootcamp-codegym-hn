package IntroductionToJava.DayOfMonth;

import java.util.Scanner;

public class DayOfMonth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which month that you want to count days?");
        int month = scanner.nextInt();
        switch (month) {
            case 1:
                System.out.println("The month '1' has 31 days!");
                break;
            case 2:
                System.out.println("the month '2' has 28 or 29 days!");
                break;
            case 3:
                System.out.println("The month '3' has 31 days!");
                break;
            case 4:
                System.out.println("The month '4' has 30 days!");
                break;
            case 5:
                System.out.println("The month '5' has 31 days!");
                break;
            case 6:
                System.out.println("The month '6' has 30 days!");
                break;
            case 7:
                System.out.println("The month '7' has 31 days!");
                break;
            case 8:
                System.out.println("The month '8' has 31 days!");
                break;
            case 9:
                System.out.println("The month '9' has 30 days!");
                break;
            case 10:
                System.out.println("The month '10' has 31 days!");
                break;
            case 11:
                System.out.println("The month '11' has 30 days!");
                break;
            case 12:
                System.out.println("The month '12' has 31 days!");
                break;
            default:
                System.out.println("Invalid input!");
                break;
                
        }
    }
}
