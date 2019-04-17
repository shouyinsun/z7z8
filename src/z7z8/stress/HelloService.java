package z7z8.stress;

/**
 * author cash
 * create 2019-04-17-23:50
 **/
public class HelloService {

    private static  HelloService instance=new HelloService();

    public static HelloService getInstance(){
        return instance;
    }

    public void sayHello(long timeMillis) {
        long time = System.currentTimeMillis() - timeMillis;
        if (time > 2000) {
            //超过2秒的打印日志输出
            System.out.println(String.format("time :%s", time));
        }
        try {
            //模拟业务执行时间为1s
            Thread.sleep(1000);
            //拿公共资源就可能造成阻塞,如db连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
