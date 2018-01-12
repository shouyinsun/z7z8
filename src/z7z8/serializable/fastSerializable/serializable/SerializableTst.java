package z7z8.serializable.fastSerializable.serializable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import z7z8.serializable.fastSerializable.Customer;

public class SerializableTst {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Order order =new Order();
		order.setDescription("序列化测试");
		order.setId(1000);
		order.setPassword("passw0rd"); 
		Customer c=new Customer();
		c.setUsername("cash");
		order.setCustomer(c);
		
		FileOutputStream out=null; 
		ObjectOutputStream oout=null; 
		
		try {
			out = new FileOutputStream("./serializable.txt");
			oout = new ObjectOutputStream(out); 
			oout.writeObject(order); 
			oout.close(); 
			oout=null;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
