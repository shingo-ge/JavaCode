package multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author shingo_ge
 */
public class CompletableFutureTest {
    public static void main(String[] args) {
        // 两个CompletableFuture执行异步查询:
        CompletableFuture<String> cfQueryFormSina = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return queryCode("中国石油", "https://finance.sina.com.cn/code/");
            }
        });
        CompletableFuture<String> cfQueryForm163 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return queryCode("中国石油","https://money.163.com/code/");
            }
        });
        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(cfQueryFormSina, cfQueryForm163);
        // 两个CompletableFuture执行异步查询:
        CompletableFuture<Double> cfFetchFromSina = cfQuery.thenApplyAsync(new Function<Object, Double>() {
            @Override
            public Double apply(Object code) {
                return fetchPrice((String)code,"https://finance.sina.com.cn/price/");
            }
        });
        CompletableFuture<Double> cfFetchFrom163 = cfQuery.thenApplyAsync(new Function<Object, Double>() {
            @Override
            public Double apply(Object code) {
                return fetchPrice((String)code,"https://money.163.com/price/");
            }
        });
        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfFetch = CompletableFuture.anyOf(cfFetchFromSina, cfFetchFrom163);
        // 最终结果:
        cfFetch.thenAccept(new Consumer<Object>() {
            @Override
            public void accept(Object result) {
                System.out.println("查询结果：" + result);
            }
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Double fetchPrice(String code, String url) {
        System.out.println("从" + url + "查询价格……");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 5 + Math.random() * 20;
    }

    private static String queryCode(String nane, String url) {
        System.out.println("从" + url + "查询代码……");
        try {
            Thread.sleep((long) (Math.random()*100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "601857";
    }
}
