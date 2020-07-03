package z7z8.interview.singleton;

/**
 * @description:
 * @author: cash
 * @create: 2020/6/15 16:46
 **/
public class Singleton2 {
    private static volatile Singleton2 instance;
    private Singleton2(){

    }
    public static  Singleton2 getInstance(){
        if(null !=instance){
            synchronized (Singleton2.class){
                if(null !=instance){
                    instance =new Singleton2();
                }
            }
        }
        return instance;
    }
}
