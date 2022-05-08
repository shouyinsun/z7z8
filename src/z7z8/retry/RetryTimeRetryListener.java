package z7z8.retry;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cash
 * @description 重试监听
 * @date 2022/1/13 上午11:18
 */
@Slf4j
public class RetryTimeRetryListener implements RetryListener {

    /**
     * 调用信息
     */
    private String callerName;

    public RetryTimeRetryListener(String callerName) {
        this.callerName = callerName;
    }

    @Override
    public <V> void onRetry(Attempt<V> attempt) {
        Long attemptNumber = attempt.getAttemptNumber();
        Long delayedMilliseconds=attempt.getDelaySinceFirstAttempt();
        //log.info("retry template,method:{},retryTimes:{},retryDelayTime:{}",callerName, attemptNumber,delayedMilliseconds);
    }
}
