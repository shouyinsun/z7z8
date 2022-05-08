package z7z8.interview.printXYZ;

/**
 * @Description
 * @Author cash
 * @Date 2022/4/24 5:44 PM
 **/
public class PrintABCUsingWaitNotify {
    /**
     * 打印的次数
     */
    private int times;
    /**
     * 打印的状态
     */
    private int state = 0;
    private Object objectA = new Object();
    private Object objectB = new Object();
    private Object objectC = new Object();

    public PrintABCUsingWaitNotify(int times) {
        super();
        this.times = times;
    }

    public void printA() {
        print("A", 0, objectA, objectB);
    }

    public void printB() {
        print("B", 1, objectB, objectC);
    }

    public void printC() {
        print("C", 2, objectC, objectA);
    }

    public void print(String name, int targetState, Object curr, Object next) {
        for (int i = 0; i < times; ) {
            synchronized (curr) {
                while (state % 3 != targetState) {
                    try {
                        curr.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                i++;
                state++;
                System.out.print(name);
                synchronized (next) {
                    next.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        PrintABCUsingWaitNotify p = new PrintABCUsingWaitNotify(10);
        new Thread(() -> p.printA()).start();
        new Thread(() -> p.printB()).start();
        new Thread(() -> p.printC()).start();
    }
}

