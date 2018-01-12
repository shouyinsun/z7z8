package z7z8.serializable.fastSerializable.kryo;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import org.apache.commons.codec.binary.Base64;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.JavaSerializer;

import z7z8.serializable.fastSerializable.Customer;
import z7z8.serializable.fastSerializable.serializable.Order;

public class KryoTst {
	
	private String s;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Order order =new Order();
		order.setDescription("序列化测试");
		order.setId(1000);
		order.setPassword("passw0rd"); 
		Customer c=new Customer();
		c.setUsername("cash");
		order.setCustomer(c);
		
		KryoTst tst=new KryoTst();
		
		tst.serializationObject(order);
		
		
		Kryo kryo2 = new Kryo();
        kryo2.setReferences(false);
        kryo2.register(Order.class, new JavaSerializer());
        
		FileInputStream in=null; 

        in = new FileInputStream("./kryo.txt"); 
        Input input = new Input(in);
        
/*        ByteArrayInputStream bais = new ByteArrayInputStream(
                new Base64().decode(s));
        Input input = new Input(bais);*/

        Order order2=(Order) kryo2.readClassAndObject(input);
        System.out.println(order2.getDescription());

	}
	
	private <T extends Serializable> String serializationObject(T obj) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(obj.getClass(),new JavaSerializer());
 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeClassAndObject(output, obj);
        
        output.flush();
        output.close();
    
        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
       /* for(byte b2:b){
        	System.out.println(b2);
        }*/
        
        s=new String(new Base64().encode(b));
        System.out.println("serializable:"+s);
        
        FileOutputStream out=null; 
		
		try {
			out = new FileOutputStream("./kryo.txt");		
			out.write(b);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
        return null;
    }

}
