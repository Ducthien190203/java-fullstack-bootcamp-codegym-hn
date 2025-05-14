package vn.codegym;

import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        double rate = 23000; 

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số tiền USD: ");
        double usd = scanner.nextDouble();


        double vnd = usd * rate;
        System.out.println(usd + " USD tương đương với " + vnd + " VND.");

        scanner.close();
    }
}
