import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static final int DISTANCE = 100;
    private static final int STEP = 5;

    private final String name;
    private final CyclicBarrier barrier;
    private int runDistance = 0;

    public Car(String name, CyclicBarrier barrier) {
        this.name = name;
        this.barrier = barrier;
    }

    public String moveAndGetLog() {
        int speed = new Random().nextInt(20);
        runDistance += speed;
        runDistance = Math.min(runDistance, DISTANCE);

        StringBuilder log = new StringBuilder("|");
        double percentTravel = (runDistance * 100.0) / DISTANCE;

        for (int i = 0; i < DISTANCE; i += STEP) {
            if (percentTravel >= i + STEP) {
                log.append("=");
            } else if (percentTravel >= i) {
                log.append("o");
            } else {
                log.append("-");
            }
        }
        log.append("|");
        return "Car " + name + ": " + log + " " + runDistance + "KM";
    }

    public boolean isFinished() {
        return runDistance >= DISTANCE;
    }

    @Override
    public void run() {
        while (!isFinished()) {
            try {
                RaceManager.logs.put(name, moveAndGetLog());
                barrier.await(); // Đợi các xe khác hoàn tất vòng này
                Thread.sleep(100); // Tránh in quá nhanh
            } catch (Exception e) {
                e.printStackTrace();
                breaok;
            }
        }
    }
}
