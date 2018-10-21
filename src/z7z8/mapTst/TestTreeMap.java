package z7z8.mapTst;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * author cash
 * create 2018-07-22-14:10
 **/
public class TestTreeMap {

    public static void main(String[] args) {
        System.out.println("开始：");

        Person person1 = new Person("cash", 220181);
        Person person2 = new Person("jack", 220193);
        Person person3 = new Person("bill", 220186);

        Map<Number, Person> map = new HashMap<Number, Person>();
        map.put(person1.getId_card(), person1);
        map.put(person2.getId_card(), person2);
        map.put(person3.getId_card(), person3);

        // HashMap
        System.out.println("HashMap，无序：");
        for (Iterator<Number> it = map.keySet().iterator(); it.hasNext();) {
            Person person = map.get(it.next());
            System.out.println(person.getId_card() + " " + person.getName());
        }

        System.out.println("---------------");

        // TreeMap
        System.out.println("TreeMap，升序：");
        TreeMap<Number, Person> treeMap = new TreeMap<>();
        treeMap.putAll(map);
        for (Iterator<Number> it = treeMap.keySet().iterator(); it.hasNext();) {
            Person person = treeMap.get(it.next());
            System.out.println(person.getId_card() + " " + person.getName());
        }

        System.out.println("---------------");

        System.out.println("TreeMap，降序：");
        TreeMap<Number, Person> treeMap2 =
                new TreeMap<>(Collections.reverseOrder());
        treeMap2.putAll(map);
        for (Iterator it = treeMap2.keySet().iterator(); it.hasNext();) {
            Person person =treeMap2.get(it.next());
            System.out.println(person.getId_card() + " " + person.getName());
        }

        System.out.println("---------------");


        System.out.println("floorEntry:"+JSONObject.toJSON(treeMap.floorEntry(220187)));

        System.out.println("ceilingEntry:"+JSONObject.toJSON(treeMap.ceilingEntry(220187)));

    }


     static class Person {
         Integer id_card;
         String name;

         public Person(String name,Integer id_card) {
             this.id_card = id_card;
             this.name = name;
         }

         public Integer getId_card() {
             return id_card;
         }

         public void setId_card(Integer id_card) {
             this.id_card = id_card;
         }

         public String getName() {
             return name;
         }

         public void setName(String name) {
             this.name = name;
         }
     }
}
