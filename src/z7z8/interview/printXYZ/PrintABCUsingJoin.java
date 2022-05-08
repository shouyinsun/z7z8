package z7z8.interview.printXYZ;

/**
 * @author cash
 * @description
 * @date 2022/4/24 6:34 PM
 */
public class PrintABCUsingJoin {
    public static void main(String[] args) {
        Thread a = new Thread(() -> System.out.print("A"));
        Thread b = new Thread(() -> {
            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("B");
        });
        Thread c = new Thread(() -> {
            try {
                b.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("C");
        });

        for (int i = 0; i < 10; i++) {
            //使用run方法
            a.run();
            b.run();
            c.run();
        }
    }
}
