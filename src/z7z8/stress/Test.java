package z7z8.stress;

/**
 * author cash
 * create 2019-04-17-23:53
 **/
public class Test {

    public static void main(String[] args) throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        //模拟1000个线程并发
        CountDownLatchUtil countDownLatchUtil = new CountDownLatchUtil(1000);
        countDownLatchUtil.latch(() -> {
            HelloService.getInstance().sayHello(currentTimeMillis);
        });
    }
}
