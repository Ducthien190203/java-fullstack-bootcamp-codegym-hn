public class ThreadMain extends Thread {
    public static void main(String[] args) {
        // Khai báo một đối tượng t tham chiếu đến thread hiện tại (Main Thread)
        Thread t = Thread.currentThread();

        // lấy ra tên Main thread
        System.out.println("Thread hiện tại: " + t.getName());

        // đổi tên Main thread sang CodeGym
        t.setName("CodeGym");
        System.out.println("Sau khi đổi tên: " + t.getName());

        // Lấy ra độ ưu tiên của Main thread
        System.out.println("Độ ưu tiên của Main Thread: " + t.getPriority());

        // Cài đặt độ ưu tiên cho Main thread (tối đa là 10)
        t.setPriority(MAX_PRIORITY);

        System.out.println("Độ ưu tiên mới của Main Thread: " + t.getPriority());

        for (int i = 0; i < 5; i++) {
            System.out.println("Main thread");
        }

        // Main thread tạo ra một thread con
        ChildThread ct = new ChildThread();

        // Cài đặt độ ưu tiên cho thread con (được kế thừa độ ưu tiên từ Thread tạo ra nó => 10)
        System.out.println("Độ ưu tiên của Child thread: " + ct.getPriority());

        // Cài độ ưu tiên cho thread con (nhỏ nhất là 1)
        ct.setPriority(MIN_PRIORITY);

        System.out.println("Độ ưu tiên mới của Child thread: " + ct.getPriority());

        ct.start();
    }
}