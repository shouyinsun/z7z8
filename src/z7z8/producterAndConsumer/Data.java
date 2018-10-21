package z7z8.producterAndConsumer;

public class Data {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Data [name=" + name + "]";
	}

	public Data(String name) {
		super();
		this.name = name;
	}
	
	
	
	

}
