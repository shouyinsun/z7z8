package z7z8.serializable.lostSomeParams.jdk;

public class Sub extends Sup {
	private Integer xx;

	public Integer getXx() {
		return xx;
	}

	public void setXx(Integer xx) {
		this.xx = xx;
	}
	
	public String toString() {
		return super.toString() + " xx: " + xx;
	}

}
