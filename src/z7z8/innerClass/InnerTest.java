package z7z8.innerClass;

public class InnerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Out.In in=new Out().new In();
        in.print();   
        //或者采用下种方式访问   
        /*   
        Out out = new Out();   
        Out.In in = out.new In();   
        in.print();   
        */   

	}

}
//外部类   
class Out {   
   private int age = 12;   

   //内部类   
   class In {   
       public void print() {   
           System.out.println(age);   
       }   
   }   
} 
