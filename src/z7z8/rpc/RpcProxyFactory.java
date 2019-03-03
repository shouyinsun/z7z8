package z7z8.rpc;

import java.lang.reflect.Proxy;

/**
 * 代理工厂
 * author cash
 * create 2019-03-03-19:54
 **/
public class RpcProxyFactory {

    public static <T> T getMultService(Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] { interfaceClass },
                new RpcNIoMultHandler());
    }
}
