package z7z8.deadlock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 简单的deadlock
 * author cash
 * create 2019-01-17-20:44
 **/
public class Simple {
    public static void main(String[] args) {
        LeftRightDeadlock leftRightDeadlock = new LeftRightDeadlock();

        new Thread(() ->
            leftRightDeadlock.leftRight()
        ).start();

        new Thread(() ->
            leftRightDeadlock.rightLeft()
        ).start();

        ThreadPoolExecutor pool =new ThreadPoolExecutor(4,10,1000, TimeUnit.SECONDS,new ArrayBlockingQueue<>(100));
        pool.submit(new Runnable() {
            @Override
            public void run() {

            }
        });

    }
}


class LeftRightDeadlock {
    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() {
        // acquire left
        synchronized (left) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // right
            synchronized (right) {
                System.out.println("I'm fine");
            }
        }
    }

    public void rightLeft() {
        // acquire right
        synchronized (right) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // left
            synchronized (left) {
                System.out.println("I'm fine too");
            }
        }
    }
}

