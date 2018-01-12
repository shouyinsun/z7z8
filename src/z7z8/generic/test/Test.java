package z7z8.generic.test;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public void print0(ArrayList<Fruit> list){
		if(null!=list && list.size()>0){
			for (int j = 0; j < list.size(); j++) {
				System.out.println(list.get(j));
			}
		}
	}
	
	
	//1
	public <T extends Fruit> void print1(ArrayList<T> list){
		if(null!=list && list.size()>0){
			for (int j = 0; j < list.size(); j++) {
				System.out.println(list.get(j));
			}
		}
	}
	
	//2
	public void print2(ArrayList<? extends Fruit> list){
		if(null!=list && list.size()>0){
			for (int j = 0; j < list.size(); j++) {
				System.out.println(list.get(j));
			}
		}
	}
	
	//6不6
	public <T extends Comparable<? super T>> T max(List<T> list){
		return null;
	}
	
	//Class 类 参数 Class<?> 直接Class亦可
	public <T> Class<?> getClassAndList(Class<?> a) {
        //Class<T>前面缺少<T>必须定义，否则将出现编译错误
        //T改成其他字母都可以，但一定要声明
        // 返回类型和参数中的类型：Class<T>和Class<?>都可以。因为返回的a的类型为Class<T>,Class<?>可以通配
        //当两个都是Class<?>，参数中的?自动通配成T
        System.out.println(a.getName());
        System.out.println(a.getClass().getName());//传入的a是一个类，Class类型
        //参数里面的Class<T>最大的好处是如果方法里面定义了泛型，可以自动获取类型值，比如如下的List<T>可以自动获取到a的类型
        List<?> aa = new ArrayList<T>();
        System.out.println(aa);
        return a;
    }
	
	public static void main(String args[]){
		Test t=new Test();

		
		ArrayList appleList=new ArrayList<Apple>();
		appleList.add(new Apple("apple1",9.50));
		appleList.add(new Apple("apple2",20.50));
		//t.print0(appleList); //compile error : Apple is subClass of Fruit but ArrayList<Apple> is not subClass of ArrayList<Fruit>
	
		ArrayList fruitList=new ArrayList<Fruit>();
		fruitList.add(new Apple("apple",12.50));
		fruitList.add(new Orange("orange",12.00));
		
		t.print0(appleList);
		t.print1(fruitList);
		t.print2(fruitList);
		
		t.getClassAndList(Apple.class);
	}

}
