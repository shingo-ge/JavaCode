package multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @author shingo_ge
 */
public class FutureTset {
    public static void main(String[] args) {
        ExecutorService es = newFixedThreadPool(4);
        Callable<String> task = new Task1("任务1");
        Future<String> future = es.submit(task);
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        es.shutdown();
    }
}
class Task1 implements Callable<String>{
    private final String name;

    public Task1(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        return null;
    }
}
