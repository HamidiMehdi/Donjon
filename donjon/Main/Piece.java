package Main;

public class Piece {
	
	private String name;
	private String description;

	public Piece(String name, String description) {
		this.name = name;
		this.description = description;
	}

	protected String getName() {
		return name;
	}

	protected String getDescription() {
		return description;
	}
}
