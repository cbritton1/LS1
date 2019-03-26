
public class Stand implements Servable {

	private String name;
	private String type;
	
	public Stand(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	@Override
	public void serve() {
		System.out.println("Serving some delicious " + type);
		
	}

	@Override
	public String toString() {
		return "Stand: " + name + " - " + " Type of beverage: " + type;
	}
	
	
}
