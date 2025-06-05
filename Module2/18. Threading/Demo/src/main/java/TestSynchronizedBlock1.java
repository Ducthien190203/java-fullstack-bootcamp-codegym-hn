class Table {
    void printTable(int n) {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println(n * i);
                try {
                    Thread.sleep(400);

                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        }
    }
}

//class MyThread1 extends Thread {
//    Table t;
//
//    MyThread1(Table t) {
//        this.t = t;
//    }
//
//    public void run() {
//        t.printTable(5);
//    }
//
//}
//
//class MyThread2 extends Thread {
//    Table t;
//
//    MyThread2(Table t) {
//        this.t = t;
//    }
//
//    public void run() {
//        t.printTable(100);
//    }
//}

public class TestSynchronizedBlock1 {
    public static void main(String[] args) {
//        Table obj = new Table();
//        MyThread1 t1 = new MyThread1(obj);
//        MyThread2 t2 = new MyThread2(obj);
//        t1.start();
//        t2.start();

        final Table obj = new Table();// tao object duy nhat

        Thread t1 = new Thread() {
            public void run() {
                obj.printTable(5);
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                obj.printTable(100);
            }
        };

        t1.start();
        t2.start();

    }
}

