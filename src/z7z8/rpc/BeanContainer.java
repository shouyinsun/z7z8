package z7z8.rpc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * bean容器
 * author cash
 * create 2019-03-03-19:32
 **/
public class BeanContainer {
    private static ConcurrentHashMap<Class<?>, Object> container = new ConcurrentHashMap<>();

    public static boolean addBean(Class<?> clazz, Object object) {
        container.put(clazz, object);
        return true;
    }

    public static Object getBean(Class<?> clazz) {
        return container.get(clazz);
    }
}
