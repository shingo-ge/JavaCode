package multithreading;

/**
 * @author shingo_ge
 */
public class InterruptThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new MyThread1();
        thread1.start();
        Thread.sleep(8);
        thread1.interrupt();//中断thread1线程
        thread1.join();//等待thread1线程结束
        System.out.println("结束");
    }
}
class MyThread1 extends Thread{
    @Override
    public void run() {
        int mun = 0;
        while (!interrupted()){
            mun++;
            System.out.println(mun + "次");
        }
    }
}
