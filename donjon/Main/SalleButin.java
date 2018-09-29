package Main;

import java.util.Random;

public class SalleButin extends Piece {

	public SalleButin(String name, String description) {
		super(name, description);
	}
	
	protected int generateButin() {
		Random r = new Random();
		return r.nextInt(11) + 20;
		// on peut gagner entre 20 et 30 de butin dans la salle butin
	}

}
