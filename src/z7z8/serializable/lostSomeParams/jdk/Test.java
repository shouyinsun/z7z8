package z7z8.serializable.lostSomeParams.jdk;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * @author xuc
 * @time 2017年2月22日 下午2:00:42
 * @description 
 * jdk的序列化,子类中覆盖的父类属性不会丢失
 */
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sub sub=new Sub();
		sub.setXx(100);
		FileInputStream in=null; 
		FileOutputStream out=null; 
		ObjectInputStream oin=null; 
		ObjectOutputStream oout=null; 
		try {
			out = new FileOutputStream("./Test3.txt");//子类序列化 
			oout = new ObjectOutputStream(out); 
			oout.writeObject(sub); 
			oout.close(); 
			oout=null; 
	
			in = new FileInputStream("./Test3.txt"); 
			oin = new ObjectInputStream(in); 
			Sub subc2=(Sub)oin.readObject();//子类反序列化 
			System.out.println(subc2.getXx()); 
		}catch (Exception ex){
			ex.printStackTrace(); 
		}finally {
			
		}
	}

}
