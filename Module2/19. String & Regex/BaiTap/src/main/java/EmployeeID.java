import java.util.Scanner;

public class EmployeeID {
    private static final String EMPLOYEE_ID_REGEX = "^NV-\\d{4}$";

    public EmployeeID() {
    }

    public static boolean validate(String regex) {
        if (regex == null) {
            return false;
        }
        return regex.matches(EMPLOYEE_ID_REGEX);
    }

    public static void main(String[] args) {
        boolean repeat = true;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Nhập mã nhân viên: ");
            String input = sc.nextLine();

            if (input.equals("p")) {
                repeat = false;
            } else if (validate(input)) {
                System.out.println("Mã nhân viên hơp lệ.");
            } else {
                System.out.println("Mã nhân viên không hợp lệ.");
            }
        } while (repeat);
        sc.close();
    }
}
