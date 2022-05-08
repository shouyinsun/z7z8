package z7z8.concurrent;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONObject;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @author cash
 * @description 限时并行处理
 * (规定时间内,有多少结果就用多少结果)
 * @date 2022/2/18 3:29 PM
 */
public class LimitedTimeParallelExecutor {

    public static <T> List<T> execute(List<Callable<T>> callables, String callerName, Long durationMs) {

        long startMs = System.currentTimeMillis();
        int taskSize = callables.size();
        long sleepMs = Math.max(durationMs / taskSize / 5, 1);
        CompletionService completionService = new ExecutorCompletionService(getExecutor(callerName)
            , new LinkedBlockingQueue(100));
        List<Future> futures = Lists.newArrayList();
        callables.stream().forEach(o -> futures.add(completionService.submit(o)));
        List<T> list = Lists.newArrayList();
        try {
            while (true) {
                long now = System.currentTimeMillis();
                if (now - startMs >= durationMs) {
                    break;
                }
                Future<T> future = completionService.poll();
                if (null != future) {
                    list.add(future.get());
                }
                if (list.size() == taskSize) {
                    break;
                }
                Thread.sleep(sleepMs);
            }
        } catch (Exception ignore) {
            //noop
        } finally {
            for (Future f : futures) {f.cancel(true);}
        }
        return list;
    }

    private static ThreadPoolExecutor getExecutor(String callerName) {
        String nameFormat = "LimitedTimeParallelExecutor-" + callerName + "-%d";
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
            .setNameFormat(nameFormat).build();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4,
            Runtime.getRuntime().availableProcessors() * 2, 1000L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(500), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
        //executor.se();
        return executor;
    }

    public static void main(String[] args) {
        List<Callable<Integer>> callableList = Lists.newArrayList();
        Callable<Integer> callable1 = (Callable)() -> {
            Thread.sleep(2 * 10L);
            return 1;
        };
        Callable<Integer> callable2 = (Callable)() -> {
            Thread.sleep(5 * 10L);
            return 2;
        };
        Callable<Integer> callable3 = (Callable)() -> {
            Thread.sleep(3 * 10L);
            return 3;
        };
        callableList.add(callable1);
        callableList.add(callable2);
        callableList.add(callable3);
        Long s = System.currentTimeMillis();
        List<Integer> resultList = LimitedTimeParallelExecutor.execute(callableList, "test", 100L);
        System.out.println("elapse time:" + (System.currentTimeMillis() - s));
        System.out.println(JSONObject.toJSONString(resultList));

    }
}
