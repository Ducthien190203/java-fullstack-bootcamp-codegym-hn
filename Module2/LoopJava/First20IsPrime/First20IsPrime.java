package LoopJava.First20IsPrime;

import java.util.Scanner;

public class First20IsPrime {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numbers;
        do {
            System.out.println("Nhập số lượng số nguyên tố");
            numbers = input.nextInt();
        } while (!(numbers > 0));
        input.close();

        int count = 0, i = 2;

        while (count != numbers) {

            boolean check = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    check = false;
                    break;
                }
            }

            if (check) {
                System.out.print(i + ", ");
                count++;
            }
            i++;
        }
    }
}
