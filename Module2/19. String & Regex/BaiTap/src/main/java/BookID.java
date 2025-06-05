import java.util.Scanner;

public class BookID {
    private static final String BOOK_ID_REGEX = "^BOOK-\\d{4}$";

    public BookID() {
    }

    public static boolean validate(String regex) {
        if (regex == null) {
            return false;
        }
        return regex.matches(BOOK_ID_REGEX);
    }

    public static void main(String[] args) {
        boolean repeat = true;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Nhập mã sách: ");
            String input = sc.nextLine();

            if (input.equals("p")) {
                repeat = false;
            } else if (validate(input)) {
                System.out.println("Mã sách hơp lệ.");
            } else {
                System.out.println("Mã sách không hợp lệ.");
            }
        } while (repeat);
        sc.close();
    }
}
