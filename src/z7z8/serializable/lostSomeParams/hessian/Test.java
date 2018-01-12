package z7z8.serializable.lostSomeParams.hessian;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

/**
 * 
 * @author xuc
 * @time 2017年2月22日 下午2:00:42
 * @description 
 * hessian序列化时,子类中覆盖的父类属性会丢失
 */
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sub sub=new Sub();
		sub.setXx(100);
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		HessianOutput out  = new HessianOutput(os);
		try {
		    out.writeObject(sub);
		    os.flush();
		    byte[] buffer = os.toByteArray();
		    HessianInput in = new HessianInput(new ByteArrayInputStream(buffer));
		    Sub p = (Sub)in.readObject(Sub.class);
		    System.out.println(p.getXx());
		} catch (IOException e) {
		    e.printStackTrace();
		}


	}

}
