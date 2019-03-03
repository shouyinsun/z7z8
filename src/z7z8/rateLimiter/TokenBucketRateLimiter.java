package z7z8.rateLimiter;

/**
 * description 令牌桶算法
 * 令牌以固定速率产生,使用令牌不耗费时间
 *
 * 令牌桶算法允许突发速率
 * author cash
 * create 2018-07-26-19:09
 **/

public class TokenBucketRateLimiter {
    private static long timestamp=System.currentTimeMillis();
    private static long capacity=200;//容量
    private static long token=100;//当前token数
    private static long rate=10;//生产速率


    public static boolean grant(){
        long now=System.currentTimeMillis();
        token=Math.min(capacity,token+(now-timestamp)/1000*rate);
        timestamp=now;
        if(token>0){
            token--;
            return true;
        }else{
            return false;
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
