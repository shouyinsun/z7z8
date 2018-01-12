package z7z8.serializable.lostSomeParams.hessian;

import java.io.Serializable;

public class Sup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5970314899281569785L;
	private Integer xx=50;
	private String  yy;
	
	
	public Integer getXx() {
		return xx;
	}
	public void setXx(Integer xx) {
		this.xx = xx;
	}
	public String getYy() {
		return yy;
	}
	public void setYy(String yy) {
		this.yy = yy;
	}
	
	public String toString() {
		return "xx: "+xx+" yy:"+yy; 
	} 
	
	

}
