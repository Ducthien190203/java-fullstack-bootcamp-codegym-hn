package vn.codegym;

import java.util.Scanner;

public class NumberToText {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number (0-999): ");
        int number = scanner.nextInt();

        if (number < 0 || number > 999) {
            System.out.println("Out of ability");
            return;
        }

        System.out.println(convertNumberToWords(number));
    }

    public static String convertNumberToWords(int number) {
        if (number == 0) return "zero";

        StringBuilder words = new StringBuilder();

        if (number >= 100) {
            int hundreds = number / 100;
            switch (hundreds) {
                case 1: words.append("one hundred"); break;
                case 2: words.append("two hundred"); break;
                case 3: words.append("three hundred"); break;
                case 4: words.append("four hundred"); break;
                case 5: words.append("five hundred"); break;
                case 6: words.append("six hundred"); break;
                case 7: words.append("seven hundred"); break;
                case 8: words.append("eight hundred"); break;
                case 9: words.append("nine hundred"); break;
            }
            number %= 100;
            if (number > 0) words.append(" and ");
        }

        if (number >= 20) {
            switch (number / 10) {
                case 2: words.append("twenty"); break;
                case 3: words.append("thirty"); break;
                case 4: words.append("forty"); break;
                case 5: words.append("fifty"); break;
                case 6: words.append("sixty"); break;
                case 7: words.append("seventy"); break;
                case 8: words.append("eighty"); break;
                case 9: words.append("ninety"); break;
            }
            if (number % 10 > 0) words.append(" ");
            number %= 10;
        } else if (number >= 10) {
            switch (number) {
                case 10: words.append("ten"); break;
                case 11: words.append("eleven"); break;
                case 12: words.append("twelve"); break;
                case 13: words.append("thirteen"); break;
                case 14: words.append("fourteen"); break;
                case 15: words.append("fifteen"); break;
                case 16: words.append("sixteen"); break;
                case 17: words.append("seventeen"); break;
                case 18: words.append("eighteen"); break;
                case 19: words.append("nineteen"); break;
            }
            return words.toString();
        }

        if (number > 0) {
            switch (number) {
                case 1: words.append("one"); break;
                case 2: words.append("two"); break;
                case 3: words.append("three"); break;
                case 4: words.append("four"); break;
                case 5: words.append("five"); break;
                case 6: words.append("six"); break;
                case 7: words.append("seven"); break;
                case 8: words.append("eight"); break;
                case 9: words.append("nine"); break;
            }
        }

        return words.toString();
    }
}
