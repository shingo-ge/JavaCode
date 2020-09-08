package multithreading;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @author shingo_ge
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        // 创建一个固定大小的线程池:
        ExecutorService es = newFixedThreadPool(4);
        for (int i = 0; i < 6; i++) {
            es.submit(new Task("线程：" + i));
        }
        // 关闭线程池:
        es.shutdown();
    }
}
class Task implements Runnable{
    private final String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("开始" + name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束" + name);
    }
}
