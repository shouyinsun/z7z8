package z7z8.interview.printXYZ;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cash
 * @description
 * @date 2022/4/24 8:32 PM
 */
public class PrintABCUsingCondition {

    private int times;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public PrintABCUsingCondition(int times) {
        super();
        this.times = times;
    }

    public void print(int target, Condition curr, Condition next) {
        for (int i = 0; i < times; ) {
            lock.lock();
            try {
                while (count % 3 != target) {
                    curr.await();
                }
                System.out.print(Thread.currentThread().getName());
                count++;
                i++;
                next.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printA() {
        print(0, c1, c2);
    }

    public void printB() {
        print(1, c2, c3);
    }

    public void printC() {
        print(2, c3, c1);
    }

    public static void main(String[] args) {
        PrintABCUsingCondition p = new PrintABCUsingCondition(10);

        new Thread(() -> p.printA(), "A").start();

        new Thread(() -> p.printB(), "B").start();

        new Thread(() -> p.printC(), "C").start();
    }
}
