package Main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Donjon {
	
	private Random r = new Random();
	private Scanner sc = new Scanner(System.in);
	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	private String name;
	private Heros heros;

	public Donjon() {
		this.name = this.getRandomNameDonjon();
		this.heros = this.getRandomHeros();
		System.out.println("Votre heros [" + this.heros.getName() + "] entre dans le donjon \"" + this.name + "\"");
		this.generatePieces();
		
		for(Piece piece : this.pieces) {
			
			if(piece instanceof Couloir) {
				System.out.println("\n\n----------------------- PIECE TYPE COULOIR -----------------------");
				System.out.println(piece.getDescription() + ", voulez vous continuer? (y/n)");
				if(this.sc.next().equals("y")) {
					((Couloir)piece).continuer();
					continue;
				}
				else {
					((Couloir)piece).partir();
					System.out.println("Vous quittez le donjon \"" + this.name + "\"");
					break;
				}
			}
			
			else if(piece instanceof SalleVide) {
				System.out.println("\n\n----------------------- PIECE TYPE SALLE VIDE -----------------------");
				System.out.println(piece.getDescription() + ", [" + this.heros.getName() + "] quitte la salle [" + piece.getName() + "] et se dirige vers le prochain couloir");
				continue;
			}
			
			else if(piece instanceof SalleMonstre) {
				System.out.println("\n\n----------------------- PIECE TYPE SALLE MONSTRE -----------------------");
				System.out.println(piece.getDescription());
				System.out.println("Combat ["+this.heros.getName()+"] vs ["+ ((SalleMonstre)piece).getMonstre().getName() +"] :");
				if( ((SalleMonstre)piece).fight(this.heros) ) {
					System.out.println("      COMBAT FINI : ["+this.heros.getName()+"] a tué ["+((SalleMonstre)piece).getMonstre().getName()+"]");
					if( this.pieces.indexOf(piece) == this.pieces.size() -1 ) { // si c'est la derniere piece de la liste (donc la salle final)
						int butin = ((SalleMonstre)piece).generateButin();
						System.out.println("Vous avez gagner "+butin+" pieces d'or en tuant ["+((SalleMonstre)piece).getMonstre().getName()+"]");
						this.heros.addButin(butin);
						System.out.println("["+this.heros.getName()+"] a fini le donjon \""+this.name+"\" et a gagné "+this.heros.getButin()+" pieces d'or");
						System.out.println("FIN DU DONJON");
					}
					else {
						System.out.println("[" + this.heros.getName() + "] quitte la salle [" + piece.getName() + "] et se dirige vers le prochain couloir");
						continue;
					}
				}
				else {
					System.out.println("Vous avez perdu contre le monstre ["+((SalleMonstre)piece).getMonstre().getName()+"]");
					break;
				}
			}
			
			else if(piece instanceof SalleButin) {
				System.out.println("\n\n----------------------- PIECE TYPE SALLE BUTIN -----------------------");
				System.out.println(piece.getDescription() + ", voulez vous ouvrir le trésor? (y/n)");
				if(this.sc.next().equals("y")) {
					this.heros.addButin( ((SalleButin)piece).generateButin() );
					System.out.print("Vous avez trouvés "+this.heros.getButin()+" pieces d'or en ouvrant le trèsor, ");
				}
				else {
					System.out.print("Vous n'avez pas ouvert le trésor, ");
				}
				System.out.print("[" + this.heros.getName() + "] quitte la salle [" + piece.getName() + "] et se dirige vers le prochain couloir");
				continue;
			}
			
		}
	}
	
	private String getRandomNameDonjon() {
		String[] noms = new String[7]; ;
		noms[0] = "Antre du Korriandre";
		noms[1] = "Arbre de Moon";
		noms[2] = "Cave du Toxoliath";
		noms[3] = "Comte Harebourg";
		noms[4] = "Grotte Hesque";
		noms[5] = "Hypogée de l'Obsidiantre";
		noms[6] = "Sanctuaire des Dragoeufs";
		return noms[this.r.nextInt(7)];
	}
	
	private Heros getRandomHeros() {
		String[] noms = new String[7]; ;
		noms[0] = "Apollon";
		noms[1] = "Aphrodite";
		noms[2] = "Athéna";
		noms[3] = "Hermès";
		noms[4] = "Arès";
		noms[5] = "Poséidon";
		noms[6] = "Zeus";
		return new Heros(noms[this.r.nextInt(7)]);
	}
	
	private void generatePieces() {
		Piece couloir1     = new Couloir("Couloir vide", "Il n'y a rien de suspect dans ce couloir");
		Piece salleVide    = new SalleVide("Salle vide", "Il n'y a rien dans cette salle");
		Piece couloir2     = new Couloir("Couloir obscure", "Le couloir est très sombre et on y entend un bruit étrange...");
		Piece salleMonstre = new SalleMonstre("Salle monstre", "Oh un monstre apparait et vous agresse !", 1);
		Piece couloir3     = new Couloir("Couloir butin", "Je sens la fortune au bout du couloir!");
		Piece salleButin   = new SalleButin("Salle butin", "Il y a un trésor par terre");
		Piece couloir4     = new Couloir("Couloir final", "Le boss final est au bout du couloir");
		Piece salleFinal   = new SalleMonstre("Salle Final", "Le monstre final apparait et vous agresse ! ", 2);
		
		this.pieces.add(couloir1);
		this.pieces.add(salleVide);
		this.pieces.add(couloir2);
		this.pieces.add(salleMonstre);
		this.pieces.add(couloir3);
		this.pieces.add(salleButin);
		this.pieces.add(couloir4);
		this.pieces.add(salleFinal);
	}

}
