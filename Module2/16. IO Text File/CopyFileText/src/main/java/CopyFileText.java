import java.io.*;
import java.util.Scanner;

public class CopyFileText {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Nhập đường dẫn file nguồn
        System.out.print("Nhập đường dẫn file nguồn: ");
        String sourcePath = sc.nextLine();

        // Kiểm tra file nguồn có tồn tại không
        File sourceFile = new File(sourcePath);
        if (!sourceFile.exists()) {
            System.out.println("File nguồn không tồn tại!");
            return;
        }

        // Nhập đường dẫn file đích
        System.out.print("Nhập đường dẫn file đích: ");
        String targetPath = sc.nextLine();

        File targetFile = new File(targetPath);

        // Kiểm tra file đích đã tồn tại chưa
        if (targetFile.exists()) {
            System.out.println("File đích đã tồn tại! Vui lòng chọn tên khác.");
            return;
        }

        int charCount = 0;

        // Sao chép file bằng Reader và Writer
        try (Reader reader = new FileReader(sourceFile);
             Writer writer = new FileWriter(targetFile)) {

            int c;
            while ((c = reader.read()) != -1) {
                writer.write(c);
                charCount++;
            }

            System.out.println("Sao chép thành công!");
            System.out.println("Số ký tự trong file: " + charCount);

        } catch (IOException e) {
            System.out.println("Lỗi khi sao chép file: " + e.getMessage());
        }
    }
}
