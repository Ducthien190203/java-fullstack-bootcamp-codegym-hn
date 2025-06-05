import java.util.Scanner;

public class ValidatePhoneNumberTest {
    public static void main(String[] args) {
        boolean repeat = true;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Nhập phone number: ");
            String input = sc.nextLine();

            if (input.equals("p")) {
                repeat = false;
            } else if (ValidatePhoneNumber.validate(input)) {
                System.out.println("Phone number hơp lệ.");
            } else {
                System.out.println("Phone number không hợp lệ.");
            }
        } while (repeat);
        sc.close();
    }
}
