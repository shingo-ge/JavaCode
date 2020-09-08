package multithreading;

/**
 * @author shingo_ge
 */
public class InterruptThreadTest02 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new MyThread2();
        t.start();
        Thread.sleep(1000);
        t.interrupt();// 中断t线程
        t.join();// 等待t线程结束
        System.out.println("结束！");
    }
}

class MyThread2 extends Thread{
    @Override
    public void run() {
        Thread hello = new HelloThread();
        hello.start();// 启动hello线程
        try {
            hello.join();// 等待hello线程结束
        } catch (InterruptedException e) {
            System.out.println("打断！！");
        }
        hello.interrupt();
    }
}

class HelloThread extends Thread{
    @Override
    public void run() {
        int n = 0;
        while (!interrupted()){
            n++;
            System.out.println(n + "次");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}