import java.util.Scanner;

public class StudentID {
    private static final String STUDENT_ID_REGEX = "^HV-\\d{4}$";

    public StudentID() {
    }

    public static boolean validate(String regex) {
        if (regex == null) {
            return false;
        }
        return regex.matches(STUDENT_ID_REGEX); //TẠO 1 MENU QUAN LY THONG TIN, THEM SUA XOA TIM KIEM
    }

    public static void main(String[] args) {
        boolean repeat = true;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Nhập mã học sinh: ");
            String input = sc.nextLine();

            if (input.equals("p")) {
                repeat = false;
            } else if (validate(input)) {
                System.out.println("Mã học sinh hơp lệ.");
            } else {
                System.out.println("Mã học sinh không hợp lệ.");
            }
        } while (repeat);
        sc.close();
    }
}
