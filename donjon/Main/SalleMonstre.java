package Main;

import java.util.Random;

public class SalleMonstre extends Piece {

	private Monstre monstre;

	public SalleMonstre(String name, String description, int niveau) {
		super(name, description);
		this.generateMonstre(niveau);
	}
	
	private void generateMonstre(int niveau) {
		Random r = new Random();
		String[] noms = new String[5];
		if(niveau == 2) { //nom pour le monstre final
			noms[0] = "Minotaure";
			noms[1] = "Phénix";
			noms[2] = "Cyclope";
			noms[3] = "Dragon";
			noms[4] = "Python";
		}
		else { // nom pour les petits monstres
			noms[0] = "Chimère";
			noms[1] = "Trolls";
			noms[2] = "Corbeaux";
			noms[3] = "Golem";
			noms[4] = "Goules";
		}
		this.monstre = new Monstre(noms[r.nextInt(5)], niveau);
	}
	
	protected Monstre getMonstre() {
		return this.monstre;
	}
	
	protected boolean fight(Heros heros) {
		while(heros.getPdv() > 0 && this.monstre.getPdv() > 0) {
			this.monstre.frappe(heros);
			if(heros.getPdv() <= 0) {
				break;
			}
			
			heros.frappe(this.monstre);
			if(this.monstre.getPdv() <= 0) {
				break;
			}
		}
		
		if(heros.getPdv() > 0) {
			return true;
		}
		else {
			return false;
		}		
	}
	
	protected int generateButin() {
		Random r = new Random();
		return r.nextInt(11) + 40;
		// on peut gagner entre 40 et 50 de butin dans la salle final
	}
}
