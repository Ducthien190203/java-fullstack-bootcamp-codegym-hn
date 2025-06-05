import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

public class RaceManager {
    public static final Map<String, String> logs = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Distance: 100KM");

        // Khởi tạo Barrier với 4 thread (3 xe + 1 main)
        CyclicBarrier barrier = new CyclicBarrier(4);

        Car carA = new Car("A", barrier);
        Car carB = new Car("B", barrier);
        Car carC = new Car("C", barrier);

        Thread threadA = new Thread(carA);
        Thread threadB = new Thread(carB);
        Thread threadC = new Thread(carC);

        threadA.start();
        threadB.start();
        threadC.start();

        int round = 1;

        while (true) {
            try {
                barrier.await(); // Đợi 3 xe chạy xong mỗi vòng

                System.out.println("--- Vòng " + round + " ---");
                for (String name : Arrays.asList("A", "B", "C")) {
                    if (logs.containsKey(name)) {
                        System.out.println(logs.get(name));
                    }
                }

                round++;
                Thread.sleep(500);

                // Kiểm tra nếu cả 3 đã về đích
                if (logs.values().stream().filter(s -> s.contains("100KM")).count() == 3) {
                    System.out.println("== Cuộc đua kết thúc ==");
                    break;
                }

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
