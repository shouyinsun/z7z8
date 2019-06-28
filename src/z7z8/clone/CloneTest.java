package z7z8.clone;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * author cash
 * create 2019-06-28-23:14
 **/
public class CloneTest {

    public static void main(String[] args) {
        List<String> list= Lists.newArrayList();
        list.add("a");

        Person p=new Person(list);
        Person p1=CloneUtils.clone(p);
        Person p2=CloneUtils.clone(p);

        p1.getList().add("b");
        p2.getList().add("c");

        System.out.println(p.getList());
        System.out.println(p1.getList());
        System.out.println(p2.getList());

    }
}
