package z7z8.serializable.lostSomeParams.subImplSeri;

/**
 * 
 * @author xuc
 * @time 2017年2月21日 下午1:22:17
 * @description 父类不实现序列化
 */
public class SuperC  {

	int supervalue; 
	public SuperC(int supervalue) { 
		this.supervalue = supervalue; 
	} 
	
	public String toString() {
		return "supervalue: "+supervalue; 
	} 
	
	public SuperC(){}//增加一个无参的constructor 

}
