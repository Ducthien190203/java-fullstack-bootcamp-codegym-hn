package LoopJava.IsPrime;

import java.util.Scanner;

public class IsPrime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number:");
        int number = sc.nextInt();
        sc.close();
        if (number >= 2) {
            int i = 2;
            boolean check = true;
            while (i <+ Math.sqrt(number)) {
                if (number % i == 0) {
                    check = false;
                    break;

                }
                i++;
            }
            System.out.println(number + (check ? " la so nguyen to" : " khong la so nguyen to"));
        } else {
            System.out.println(number + " ko la so nguyen to");
        }

    }
    
}
