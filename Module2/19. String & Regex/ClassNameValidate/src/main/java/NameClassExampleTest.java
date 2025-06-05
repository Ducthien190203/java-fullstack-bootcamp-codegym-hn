import java.util.Scanner;

public class NameClassExampleTest {
    public static void main(String[] args) {
        boolean repeat = true;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Nhập tên lớp: ");
            String input = sc.nextLine();

            if (input.equals("p")) {
                repeat = false;
            } else if (NameClassExample.validate(input)) {
                System.out.println("Tên lớp hơp lệ.");
            } else {
                System.out.println("Tên lớp không hợp lệ.");
            }
        } while (repeat);
        sc.close();
    }
}
