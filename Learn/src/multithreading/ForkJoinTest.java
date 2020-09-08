package multithreading;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author shingo_ge
 */
public class ForkJoinTest {
    public static void main(String[] args) {
        // 创建2000个随机数组成的数组:
        long[] array = new long[2000];
        long expectedSum = 0;
        long expectedStartTime = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = random();
            expectedSum += array[i];
        }
        long expectedEndTime = System.currentTimeMillis();
        System.out.println("使用普通方法计算，总和：" + expectedSum + "，耗时：" +
                (expectedEndTime - expectedStartTime) + "毫秒。");
        // fork/join:
        ForkJoinTask<Long> task = new SumTask(array, 0, array.length);
        long forkJoinStartTime = System.currentTimeMillis();
        Long result = ForkJoinPool.commonPool().invoke(task);
        long forkJoinEndTime = System.currentTimeMillis();
        System.out.println("使用Fork/Join方法计算，总和：" + result + "，耗时：" +
                (forkJoinEndTime - forkJoinStartTime) + "毫秒。");
    }

    static Random random = new Random(0);

    static long random() {
        return random.nextInt(10000);
    }
}

class SumTask extends RecursiveTask<Long>{
    private static final int THRESHOLD = 500;
    private static final long serialVersionUID = 2977448271744393775L;
    private final long[] array;
    private final int startIndex;
    private final int endIndex;

    public SumTask(long[] array, int startIndex, int endIndex) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    protected Long compute() {
        if (endIndex - startIndex <= THRESHOLD){
            // 如果任务足够小,直接计算:
            long sum = 0;
            for (int i = startIndex; i < endIndex; i++) {
                sum += this.array[i];

            }
            return sum;
        }
        // 任务太大,一分为二:
        int middleIndex = (startIndex + endIndex) / 2;
        System.out.printf("分割 %d~~%d 为 %d~~%d 和 %d~%d%n",startIndex,endIndex,startIndex,middleIndex,middleIndex,endIndex);
        SumTask subTask1 = new SumTask(this.array,startIndex,middleIndex);
        SumTask subTask2 = new SumTask(this.array,middleIndex,endIndex);
        invokeAll(subTask1,subTask2);
        Long subResult1 = subTask1.join();
        Long subResult2 = subTask2.join();
        Long result = subResult1 + subResult2;
        System.out.println("结果：" + subResult1 + "+" + subResult2 + "=" + result);
        return result;
    }
}
