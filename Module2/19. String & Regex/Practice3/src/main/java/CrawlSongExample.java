import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlSongExample {

    public static void main(String[] args) {
        try {
            Connection connection = Jsoup.connect("https://www.nhaccuatui.com/bai-hat/nhac-tre-moi.html")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
                    .referrer("https://www.google.com/")
                    .header("Accept-Language", "vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .timeout(15000)
                    .method(Connection.Method.GET)
                    .followRedirects(true);

            // Lấy cookies từ server (nếu cần)
            Connection.Response response = connection.execute();
            Document doc = response.parse();

            Elements songs = doc.select("a.name_song");
            for (Element song : songs) {
                System.out.println(song.text());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
                                                                    