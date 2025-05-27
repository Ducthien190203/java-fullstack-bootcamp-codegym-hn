import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập 1 chuôi kí tự:");
        String input = scanner.nextLine();

        System.out.println("Chuỗi tăng dần lớn nht là:"+LongestIncreasingSubsequenceInString.findIncreasingSubsequence(input));

    }
}
