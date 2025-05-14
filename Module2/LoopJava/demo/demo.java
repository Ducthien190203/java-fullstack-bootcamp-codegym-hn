package LoopJava.demo;

import java.util.Scanner;

public class demo {
    public static void main(String[] args) {
        int i;
        System.out.println("nhập n vào?");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (i = 0; i < n; i++) {
            System.out.println(i);
        }
        sc.close();

    }

}
