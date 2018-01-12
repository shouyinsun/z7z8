package z7z8.z7z8Test;

public class IsAssignableFrom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Testxx testxx=new Testxx();
		Testxxx testxxx=new Testxxx();
		Class clz=testxx.getClass();
		Class clz2=testxxx.getClass();
		System.out.println(clz.isAssignableFrom(clz2));

	}
	
}

class Testxx{
	
}

class Testxxx extends Testxx{
	public void test(){
			
	}
}
