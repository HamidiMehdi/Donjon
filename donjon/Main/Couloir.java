package Main;

public class Couloir extends Piece {

	public Couloir(String name, String description) {
		super(name, description);
	}
	
	protected void partir() {
		System.out.println("Vous �tes sortis du couloir [" + this.getName() + "]");
	}
	
	protected void continuer() {
		System.out.println("Vous avez travers� le couloir [" + this.getName() + "]");
	}
	
}
