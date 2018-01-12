package z7z8.serializable.lostSomeParams.supImplSeri;

import java.io.Serializable;

/**
 * 
 * @author xuc
 * @time 2017年2月21日 下午1:22:17
 * @description 父类实现序列化
 */
public class SuperC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4408564232823475578L;
	
	int supervalue; 
	public SuperC(int supervalue) { 
		this.supervalue = supervalue; 
	} 
	
	public String toString() {
		return "supervalue: "+supervalue; 
	} 

}
