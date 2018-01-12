package z7z8.serializable.fastSerializable;

import java.io.Serializable;

public class Customer  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -77960046362150458L;
	private String username;
	private Long userId;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	

}
