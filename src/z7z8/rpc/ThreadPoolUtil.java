package z7z8.rpc;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * author cash
 * create 2019-03-03-19:25
 **/
public class ThreadPoolUtil {

    private static ThreadPoolExecutor executor;

    public static void init(){
        if(null==executor){
            synchronized (ThreadPoolUtil.class){
                if(null==executor){
                    executor=new ThreadPoolExecutor(10,20,2000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
                }
            }
        }
    }

    public static void addTask(RpcNioMultServerTask task){
        if(executor==null){
            init();
        }

        executor.execute(task);

    }
}
