import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Nhap duong dan file:");
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        OverrideTextFile.overrideTextFile(path);

        ReadFileExample readfileEx = new ReadFileExample();
        readfileEx.readFileText(path);

    }
}
