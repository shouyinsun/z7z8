package z7z8.rateLimiter;

/**
 * description 漏桶算法
 * 桶容量固定,流出速率一定
 * 如果瞬间大流量,会有大量的请求丢失
 *
 * author cash
 * create 2018-07-26-18:59
 **/

public class LeakyBucketRateLimiter {
    private static long timestamp=System.currentTimeMillis();
    private static long capacity=200;//容量
    private static long water=0;//当前水量
    private static long rate=10;//流出速率

    public static boolean grant(){
        long now=System.currentTimeMillis();
        water=Math.max(0,water-(now-timestamp)/1000*rate);
        timestamp=now;
        if(water>capacity){
            return false;
        }else{
            water++;
            return true;
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<500;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(grant()){
                        System.out.println("执行业务逻辑");
                    }else {
                        System.out.println("被限流了");
                    }
                }
            }).start();
        }
    }

}
