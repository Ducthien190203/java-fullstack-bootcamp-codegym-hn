import java.util.Map;
import java.util.TreeMap;

public class WordCount {

    public static void main(String[] args) {
        String text = "This is a simple test. This test is only a test.";

        // Chuyển toàn bộ về chữ thường và loại bỏ dấu câu
        text = text.toLowerCase().replaceAll("[^a-z\\s]", "");

        // Tách chuỗi thành mảng từ
        String[] words = text.split("\\s+");

        // Tạo TreeMap để lưu từ và số lần xuất hiện
        Map<String, Integer> wordMap = new TreeMap<>();

        for (String word : words) {
            if (wordMap.containsKey(word)) {
                // Tăng số lượng nếu từ đã tồn tại
                wordMap.put(word, wordMap.get(word) + 1);
            } else {
                // Nếu chưa có thì thêm mới với số lượng 1
                wordMap.put(word, 1);
            }
        }

        // Hiển thị kết quả
        System.out.println("Từ và số lần xuất hiện (theo thứ tự bảng chữ cái):");
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
