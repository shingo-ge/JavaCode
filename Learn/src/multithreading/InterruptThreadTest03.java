package multithreading;

/**
 * @author shingo_ge
 */
public class InterruptThreadTest03 {
    public static void main(String[] args) throws InterruptedException {
        HelloThread1 t = new HelloThread1();
        t.start();
        Thread.sleep(10);
        t.setRunning(false);
    }
}
class HelloThread1 extends Thread{
    private volatile boolean running = true;

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        int n = 0;
        while (running){
            n++;
            System.out.println(n + "次");
        }
        System.out.println("结束！");
    }
}
