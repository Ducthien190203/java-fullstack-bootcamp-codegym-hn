import java.util.InputMismatchException;
import java.util.Scanner;

public class ConvertTemperature {

    public static double celsiusToFahrenheit(double celsius) {
        return (9.0 / 5) * celsius + 32;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (5.0 / 9) * (fahrenheit - 32);
    }

    public static void printMenu() {
        System.out.println("\n=== Temperature Converter Menu ===");
        System.out.println("1. Fahrenheit to Celsius");
        System.out.println("2. Celsius to Fahrenheit");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            printMenu();
            try {
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter Fahrenheit: ");
                        double fahrenheit = input.nextDouble();
                        double celsius = fahrenheitToCelsius(fahrenheit);
                        System.out.printf("%.2f°F = %.2f°C\n", fahrenheit, celsius);
                        break;
                    case 2:
                        System.out.print("Enter Celsius: ");
                        celsius = input.nextDouble();
                        fahrenheit = celsiusToFahrenheit(celsius);
                        System.out.printf("%.2f°C = %.2f°F\n", celsius, fahrenheit);
                        break;
                    case 0:
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select 0, 1, or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine(); // Đọc bỏ dòng sai để tránh lặp vô hạn
            }
        }

        input.close();
    }
}
