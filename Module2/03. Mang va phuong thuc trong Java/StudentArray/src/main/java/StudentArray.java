import java.util.Scanner;

public class StudentArray {
    public static void main(String[] args) {
        String[] students = {"Alice", "Bob", "Charlie", "David", "Eve"};
        Scanner sc = new Scanner(System.in);
        String inputName;

        while (true) {
            inputName = inputValidName(sc);

            if (inputName.equalsIgnoreCase("exit")) {
                System.out.println("Exiting program.");
                break;
            }

            printStudentSearchResult(students, inputName);
        }

        sc.close();
    }

    private static String inputValidName(Scanner sc) {
        String inputName;
        do {
            System.out.print("Enter a name: ");
            while (!sc.hasNextLine()) {
                System.out.print("Invalid input. Please enter a name: ");
                sc.next();
            }
            inputName = sc.nextLine().trim();
            if (inputName.isEmpty()) {
                System.out.println("Name cannot be empty.");
            }
        } while (inputName.isEmpty());
        return inputName;
    }

    private static void printStudentSearchResult(String[] students, String inputName) {
        for (int i = 0; i < students.length; i++) {
            if (students[i].equalsIgnoreCase(inputName)) {
                System.out.println("Name found at index: " + i);
                return;
            }
        }
        System.out.println("Name not found in the list.");
    }
}
