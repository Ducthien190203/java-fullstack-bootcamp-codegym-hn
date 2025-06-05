import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String filePath = "D:\\java-fullstack-bootcamp-codegym-hn\\Module2\\16. IO Text File\\ReadFileCSV\\countries.csv"; // Đường dẫn file CSV

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;

            System.out.println("Danh sách quốc gia:");

            while ((line = br.readLine()) != null) {
                // Tách dòng thành các phần tử bằng dấu phẩy
                String[] parts = line.split(",");

                // Lấy từng phần tử, bỏ dấu " nếu có
                String id = parts[0];
                String code = parts[1].replace("\"", "");
                String name = parts[2].replace("\"", "");

                // In ra thông tin
                System.out.println("ID: " + id + ", Code: " + code + ", Name: " + name);
            }

            br.close(); // Đóng file
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }
}
