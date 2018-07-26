package z7z8.rateLimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * description guava 的 rateLimiter
 * author cash
 * create 2018-07-26-19:46
 **/

public class GuavaRateLimiter {

    public static ConcurrentHashMap<String,RateLimiter> resourceRateLimiter=
            new ConcurrentHashMap<>();

    static{
        createResourceLimiter("query",50);
    }

    public static void createResourceLimiter(String resource,double qps){
        if(resourceRateLimiter.contains(resource)){
            resourceRateLimiter.get(resource).setRate(qps);
        }else{
            RateLimiter rateLimiter=RateLimiter.create(qps);
            resourceRateLimiter.putIfAbsent(resource,rateLimiter);
        }
    }


    public static void main(String[] args) {
        for(int i=0;i<500;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(resourceRateLimiter.get("query").tryAcquire(10,TimeUnit.MILLISECONDS)){
                        System.out.println("执行业务逻辑");
                    }else {
                        System.out.println("被限流了");
                    }
                }
            }).start();
        }
    }



}
