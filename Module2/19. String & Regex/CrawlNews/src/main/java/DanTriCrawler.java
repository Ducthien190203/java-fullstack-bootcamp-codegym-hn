import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DanTriCrawler {
    public static void main(String[] args) {
        try {
            // Gửi HTTP request đến trang
            String url = "https://dantri.com.vn/";
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .get();

            // Chọn các thẻ chứa tiêu đề bài viết
            Elements titles = doc.select("h3.article-title a");

            // In ra từng tiêu đề
            int index = 1;
            int count = 0;

            for (Element title : titles) {
                System.out.println(index++ + ". " + title.text());
                count++;
                if (count == 20) break;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
