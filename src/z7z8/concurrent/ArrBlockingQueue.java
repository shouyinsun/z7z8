package z7z8.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cash
 * @description 数组阻塞队列
 * @date 2022/4/19 1:37 PM
 */
public class ArrBlockingQueue<T> {
    private int capacity;
    private Object[] items;
    /**
     * 读写双指针
     */
    private int putIndex = 0;
    private int takeIndex = 0;
    private int count = 0;
    private ReentrantLock lock;
    /**
     * notFull
     */
    Condition notFull = lock.newCondition();
    /**
     * notEmpty
     */
    Condition notEmpty = lock.newCondition();

    public ArrBlockingQueue(int capacity) {
        this.capacity = capacity;
        items = new Object[capacity];
    }

    public void put(T element) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (count == capacity) {
                notFull.await();
            }
            items[putIndex] = element;
            count++;
            putIndex = (putIndex + 1) % capacity;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            T element = (T)items[takeIndex];
            count--;
            takeIndex = (takeIndex + 1) % capacity;
            notFull.signal();
            return element;
        } finally {
            lock.unlock();
        }
    }

    public T peek() {
        lock.lock();
        try {
            return (T)items[takeIndex];
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

}
