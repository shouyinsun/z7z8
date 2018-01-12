package z7z8.serializable.fastSerializable.externalizable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import z7z8.serializable.fastSerializable.Customer;

public class externalizableTst {

	public static void main(String[] args) throws IOException {
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

		out = new FileOutputStream("./externalizable.txt");
		oout = new ObjectOutputStream(out); 
		order.writeExternal(oout);
		oout.close();
		oout=null;
		

	}

}
