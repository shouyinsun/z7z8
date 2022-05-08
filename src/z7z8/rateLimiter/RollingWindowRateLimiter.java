package z7z8.rateLimiter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 滑动时间窗口
 * @Author cash
 * @Date 2022/4/19 3:09 PM
 **/

public class RollingWindowRateLimiter {

    private Long qps;
    /**
     * 跳跃数组,时间过期,直接覆盖
     */
    private Bucket[] buckets = new Bucket[10];

    public RollingWindowRateLimiter(Long qps) {
        this.qps = qps;
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new Bucket();
        }
    }

    class Bucket {
        Long timestamp = 0L;
        AtomicInteger count = new AtomicInteger(0);
    }

    public boolean acquire() {
        Long timeMillis = System.currentTimeMillis();
        int index = getIndex(timeMillis);
        Long bucketTime = timeMillis / 100 * 100;
        if (bucketTime > buckets[index].timestamp) {
            //时间过期,直接覆盖
            buckets[index].timestamp = bucketTime;
            buckets[index].count = new AtomicInteger(0);
        }
        long accumulate = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (!isStale(buckets[i], timeMillis)) {
                accumulate += buckets[i].count.get();
            }
        }
        if (accumulate > qps) {
            return false;
        } else {
            buckets[index].count.incrementAndGet();
        }
        return true;
    }

    private int getIndex(Long timestamp) {
        return (int)(timestamp / 100 * 100 % 1000 / 100);
    }

    private boolean isStale(Bucket bucket, Long currentTime) {
        return Math.abs(bucket.timestamp - currentTime) >= 1000;
    }

    public static void main(String[] args) {
        RollingWindowRateLimiter limiter = new RollingWindowRateLimiter(10L);
        for (int i = 0; i < 100; i++) {
            System.out.println(System.currentTimeMillis() + ":" + limiter.acquire());
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


