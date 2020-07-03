package z7z8.interview;

import java.util.Comparator;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @description: 使用3个线程，1个线程打印X，一个线程打印Y，一个线程打印Z，同时执行连续打印10次"XYZ"
 * @author: cash
 * @create: 2020/6/15 9:51
 **/
public class PrintNum {
    private AtomicInteger count = new AtomicInteger(30);

    public void print() {
        Thread t1 = new Thread(new Runnable() {
          public void run() {
                while (count.get() > 0) {
                    if (count.get() % 3 == 0) {
                        System.out.println("X");
                        count.decrementAndGet();
                    }
                }

            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                while (count.get() > 0) {
                    if (count.get() % 3 == 2) {
                        System.out.println("Y");
                        count.decrementAndGet();
                    }
                }

            }
        });
        Thread t3 = new Thread(new Runnable() {
            public void run() {
                while (count.get() > 0) {
                    if (count.get() % 3 == 1) {
                        System.out.println("Z");
                        System.out.println();
                        count.decrementAndGet();
                    }
                }

            }
        });

        t1.start();
        t2.start();
        t3.start();
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        PrintNum printNum =new PrintNum();
        printNum.print();
        Semaphore semaphore =new Semaphore(30);
        semaphore.acquire(1);
        CountDownLatch countDownLatch =new CountDownLatch(30);
        countDownLatch.countDown();
        CyclicBarrier cyclicBarrier =new CyclicBarrier(30);
        cyclicBarrier.await();
        LongAdder longAdder =new LongAdder();
        longAdder.increment();
        longAdder.sum();
        TreeMap<String,String> treeMap =new TreeMap();
        treeMap.ceilingEntry("a");
        Comparator comparator =new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
        Map.Entry entry = new Map.Entry() {
            @Override
            public Object getKey() {
                return null;
            }

            @Override
            public Object getValue() {
                return null;
            }

            @Override
            public Object setValue(Object value) {
                return null;
            }
        };
        Stack<String> stack =new Stack<>();

       ForkJoinPool forkJoinPool=new ForkJoinPool();

    }
}