package z7z8.retry;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicate;

/**
 * @author cash
 * @description 重试模板
 * @date 2022/1/13 上午11:18
 */
public class RetryTemplate {

    /**
     * 方法重试
     * @param sleepTime 间隔时间
     * @param retryTimes 重试次数
     * @param callable  调用方法
     * @param resultPredicate 结果
     * @param callerName 调用方
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T executeWithRetry(Integer sleepTime, Integer retryTimes, Callable<T> callable, Predicate<T> resultPredicate,
                                         String callerName) throws Exception {

        Retryer<T> retry = RetryerBuilder.<T>newBuilder()
            //异常重试
            .retryIfException()
            //返回值重试
            .retryIfResult(resultPredicate)
            //重试间隔
            .withWaitStrategy(WaitStrategies.fixedWait(sleepTime, TimeUnit.MILLISECONDS))
            .withStopStrategy(StopStrategies.stopAfterAttempt(retryTimes))
            //重试监听
            .withRetryListener(new RetryTimeRetryListener(callerName))
            .build();

        T result = retry.call(callable);
        return result;
    }
}
