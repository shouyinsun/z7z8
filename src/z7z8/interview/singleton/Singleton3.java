package z7z8.interview.singleton;

/**
 * @description: 静态内部类
 * @author: cash
 * @create: 2020/6/15 16:56
 **/
public class Singleton3 {
    private Singleton3(){

    }

    public static Singleton3 getInstance(){
        return SingletonHolder.instance;
    }

    private static class SingletonHolder{
        //静态属性
        private static Singleton3 instance =new Singleton3();
    }
}
