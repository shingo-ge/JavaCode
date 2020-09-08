package multithreading;

/**
 * @author shingo_ge
 */
public class SynchronizeTest {
    public static void main(String[] args) throws InterruptedException {
        var add = new AddThread();
        var dec = new DecThread();
        add.start();
        dec.start();
        add.join();
        dec.join();
        System.out.println(Calculator.count);
    }
}
class Calculator {
    public static final Object lock = new Object();
    public static int count = 0;
}
class AddThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Calculator.lock){
                Calculator.count += 1;
            }
        }
    }
}
class DecThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Calculator.lock){
                Calculator.count -= 1;
            }
        }
    }
}
