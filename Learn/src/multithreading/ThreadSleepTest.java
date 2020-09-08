package multithreading;

/**
 * @author shingo_ge
 */
public class ThreadSleepTest {
    public static void main(String[] args) {
        System.out.println("主线程启动！");
        Thread thread = new Thread(() -> {
            System.out.println("子线程启动");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子现场结束");
        });
        thread.start();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束！");
    }
}
