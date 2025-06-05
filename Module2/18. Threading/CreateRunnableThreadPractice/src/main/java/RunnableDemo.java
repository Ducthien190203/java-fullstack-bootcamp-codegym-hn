public class RunnableDemo implements Runnable {
    private Thread t;
    private final String threadName;

    public RunnableDemo(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        try {
            System.out.println("Running " + threadName);
            for (int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread " + threadName + " exiting.");
    }
    public void start() {
        System.out.println("Starting "+threadName);
        if (t==null) {
            t=new Thread(this,threadName);
            t.start();
        }
    }


}


