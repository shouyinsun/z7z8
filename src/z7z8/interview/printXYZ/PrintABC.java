package z7z8.interview.printXYZ;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 使用3个线程，1个线程打印X，一个线程打印Y，一个线程打印Z，同时执行连续打印10次"XYZ"
 * @author: cash
 * @create: 2020/6/15 9:51
 **/
public class PrintABC {
    private AtomicInteger count = new AtomicInteger(30);

    private Lock lock = new ReentrantLock();

    public void print() {
        Thread t1 = new Thread(() -> {
              while (count.get() > 0) {
                  lock.lock();
                  try {
                      if (count.get() % 3 == 0 && count.get() > 0) {
                          System.out.print("A");
                          count.decrementAndGet();
                      }
                  }finally {
                      lock.unlock();
                  }
              }

          });
        Thread t2 = new Thread(() -> {
            while (count.get() > 0) {
                if (count.get() % 3 == 2) {
                    System.out.print("B");
                    count.decrementAndGet();
                }
            }

        });
        Thread t3 = new Thread(() -> {
            while (count.get() > 0) {
                if (count.get() % 3 == 1) {
                    System.out.print("C");
                    count.decrementAndGet();
                }
            }

        });

        t1.start();
        t2.start();
        t3.start();
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        PrintABC printABC1 =new PrintABC();
        printABC1.print();
    }
}