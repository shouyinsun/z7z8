package z7z8.rpc;

/**
 * rpc消费端
 * author cash
 * create 2019-03-03-19:56
 **/
public class RpcNioConsumer {

    public static void main(String[] args) {
        multipartRpcNio();
    }


    public static void multipartRpcNio() {//多线程
        HelloService proxy = RpcProxyFactory.getMultService(HelloService.class);
        for (int i = 0; i < 100; i++) {
            final int j = i;
            Runnable runnable = () -> {
                String result = proxy.sayHello("world "+j+" !");
                System.out.println(result);
            };
            Thread t = new Thread(runnable);
            t.start();
        }
    }
}
