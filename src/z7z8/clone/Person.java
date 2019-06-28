package z7z8.clone;

import java.io.Serializable;
import java.util.List;

/**
 * author cash
 * create 2019-06-28-23:06
 **/
public class Person implements Serializable {



    private static final long serialVersionUID = -5104432951024099669L;
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Person(List<String> list) {
        this.list = list;
    }
}
