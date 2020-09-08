package multithreading;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author shingo_ge
 */
public class WaitAndNotifyTest {
    public static void main(String[] args) throws InterruptedException {
        TaskQueue tq = new TaskQueue();
        List<Thread> executeThreadList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread executeThread = new Thread(){
                @Override
                public void run() {
                    // 执行task:
                    while (true){
                        try {
                            String taskName = tq.getTask();
                            System.out.println("执行任务：" + taskName);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }
            };
            executeThread.start();
            executeThreadList.add(executeThread);
        }
        Thread addThread = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    // 放入task:
                    String taskName = "任务：" + Math.random();
                    System.out.println("添加任务：" + taskName);
                    tq.addTask(taskName);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        addThread.start();
        addThread.join();
        Thread.sleep(100);
        for (Thread thread : executeThreadList) {
            thread.interrupt();
        }
    }
}
class TaskQueue{
    private final Queue<String> queue = new LinkedList<>();
    public synchronized void addTask(String taskName){
        queue.add(taskName);
        this.notifyAll();
    }
    public synchronized String getTask() throws InterruptedException {
        while (queue.isEmpty()){
            this.wait();
        }
        return queue.remove();
    }
}
