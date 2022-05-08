package z7z8.z7z8Test;

public class FieldHidden {
	/**
	 * 
	 * @author cash
	 * @date 2017年12月5日 上午10:53:39
	 * @decription 子类与父类有相同的成员变量,父类的成员变量不会覆盖,只会隐藏
	 * 子类中,父类的成员变量不能被简单的用引用来访问 而是,必须从父类的引用获得父类被隐藏的成员变量
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sub c1 = new Sub();   
        System.out.println(" c1.s : " + c1.s);   
        System.out.println(" c1.say : " + c1.say());   

        Super c2 = new Sub();   
        System.out.println(" c2.s : " + c2.s);   
        System.out.println(" c2.say : " + c2.say());   

	}

}
class Super {   
    String s = "Super";   

    String say(){   
        return "hello Super";   
    }   
}   

class Sub extends Super {   
    String s = "Sub";   

    @Override
    String say(){
        return "hello Sub";   
    }   
}
