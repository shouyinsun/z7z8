package z7z8.interview.singleton;

/**
 * @description:
 * @author: cash
 * @create: 2020/6/15 16:46
 **/
public class Singleton1 {
    private static Singleton1 instance=new Singleton1();
    private Singleton1(){

    }
    public static Singleton1 getInstance(){
        return instance;
    }
}
