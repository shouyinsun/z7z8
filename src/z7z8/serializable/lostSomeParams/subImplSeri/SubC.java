package z7z8.serializable.lostSomeParams.subImplSeri;

import java.io.IOException;
import java.io.Serializable;

/**
 * 
 * @author xuc
 * @time 2017年2月21日 下午1:21:30
 * @description 
 * 一个没有实现Serializable接口的父类,编写一个能够序列化的子类
 *  1. 父类要有一个无参的constructor
 *  2. 子类要先序列化自身,然后子类要负责序列化父类的域
 */
public class SubC extends SuperC implements Serializable {

	int subvalue;

	public SubC(int supervalue, int subvalue) {
		super(supervalue);
		this.subvalue = subvalue;
	}

	public String toString() {
		return super.toString() + " sub: " + subvalue;
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException{
		out.defaultWriteObject();//先序列化对象 
		out.writeInt(supervalue);//再序列化父类的域 
	}
	
	private void readObject(java.io.ObjectInputStream in) 
			throws IOException, ClassNotFoundException{ 
		in.defaultReadObject();//先反序列化对象 
		supervalue=in.readInt();//再反序列化父类的域 			　　
	} 

}
