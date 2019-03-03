package z7z8.innerClass.staticInner;

class Out {   
    private static int age = 12;   

    //static 将内部内静态化,那么内部类就只能访问外部类的静态成员变量,具有局限性
    static class In {   
        public void print() {   
            System.out.println(age);   
        }   
    }   
}   

public class Demo {
	
    public static void main(String[] args) {   
        Out.In in = new Out.In();   
        in.print();   
    }   
}
