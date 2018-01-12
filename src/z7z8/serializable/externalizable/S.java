package z7z8.serializable.externalizable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;


/**
 * 
 * @author xuc
 * @time 2017年2月23日 下午5:19:50
 * @description
 * Externalizable 也是实现  Serializable 接口
 * 但是需要手动实现 readExternal 和   writeExternal 方法,自己实现序列化
 */

public class S implements Externalizable {

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		 System.out.println("writeExternal invoked");  
		 out.writeObject("Hello world"); 
		
	}
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("readExternal invoked"); 
		Object obj = in.readObject();
		System.out.println(obj.toString());
		
	} 
	
	public Object serialize() throws IOException, ClassNotFoundException {  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        ObjectOutputStream oos = new ObjectOutputStream(baos);  
        oos.writeObject(this);  
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());  
        ObjectInputStream ois = new ObjectInputStream(bais);  
        return ois.readObject();  
    }  
	
	 public static void main(String[] args) throws IOException,   
     ClassNotFoundException {  
		 S s = new S();  
         s.serialize();  
	 }  
		

}
