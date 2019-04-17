package z7z8.stress;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch
 * author cash
 * create 2019-04-17-23:37
 **/
public class CountDownLatchUtil {
    private CountDownLatch start;
    private CountDownLatch end;
    private int poolSize = 10;

    public CountDownLatchUtil() {
        this(10);
    }

    public CountDownLatchUtil(int pollSize) {
        this.poolSize = pollSize;
        start = new CountDownLatch(1);
        end = new CountDownLatch(pollSize);
    }

    public void latch(FunctionalInterface functionalInterface) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < poolSize; i++) {
            Runnable run = () -> {
                try {
                    //等待任务全部提交之后再执行
                    start.await();
                    functionalInterface.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    end.countDown();
                }
            };
            executorService.submit(run);
        }
        start.countDown();
        end.await();
        executorService.shutdown();
    }

    @java.lang.FunctionalInterface
    public interface FunctionalInterface {
        void run();
    }

}
