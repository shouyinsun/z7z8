package z7z8.rateLimiter;

/**
 * description 计数限流  会有临界点的问题,不够平滑
 * author cash
 * create 2018-07-26-18:43
 **/

public class CounterRateLimiter {
    private static long timestamp=System.currentTimeMillis();
    private static long limitCount=200;
    private static  int interval=1;//1s
    private static long requestCnt=0;

    public static boolean grant(){
        long now=System.currentTimeMillis();
        if(now-timestamp>=interval*1000){
            timestamp=now;
            requestCnt=1;
            return true;
        }else{
            requestCnt++;
            if(requestCnt>limitCount){
                return false;
            }
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
