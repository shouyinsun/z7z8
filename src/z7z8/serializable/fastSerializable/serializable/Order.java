package z7z8.serializable.fastSerializable.serializable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import z7z8.serializable.fastSerializable.Customer;

public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -656497195279063943L;
	private long id;
	private String description;
	private transient String password;//密码不能被序列化
	private BigDecimal totalCost = BigDecimal.valueOf(0);
	private List orderLines = new ArrayList();
	private Customer customer = new Customer();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public List getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List orderLines) {
		this.orderLines = orderLines;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
