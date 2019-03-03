package z7z8.rpc;

/**
 * author cash
 * create 2019-03-03-18:49
 **/
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello "+name;
    }
}
