package z7z8.serializable.lostSomeParams.supImplSeri;

/**
 * 
 * @author xuc
 * @time 2017年2月21日 下午1:21:30
 * @description  子类继承父类,父类实现序列化,子类自动实现
 */
public class SubC extends SuperC {

	int subvalue;

	public SubC(int supervalue, int subvalue) {
		super(supervalue);
		this.subvalue = subvalue;
	}

	public String toString() {
		return super.toString() + " sub: " + subvalue;
	}

}
