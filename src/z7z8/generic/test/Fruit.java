package z7z8.generic.test;

public class Fruit {
	private String name;
	private double price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Fruit [name=" + name + ", price=" + price + "]";
	}
	public Fruit(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	
	
	
	
	
	
}
