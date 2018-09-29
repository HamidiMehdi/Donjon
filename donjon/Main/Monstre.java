package Main;

import java.util.Random;

public class Monstre extends Personnage {
	
	private Random r = new Random();
	private int pdv;
	private int power;
	
	public Monstre(String name, int niveau) {
		super(name);
		this.generatePdv(niveau);
		this.generatePower(niveau);
	}
	
	private void generatePdv(int niveau) {
		if(niveau == 2) { // monstre de niveau 2 (boss final)
			this.pdv = this.r.nextInt(11) + 50;
			// un monstre de niveau 2 peut avoir entre 50 et 60 points de vie
		}
		else { // monstre de niveau 1
			this.pdv = this.r.nextInt(11) + 20;
			// un monstre de niveau 1 peut avoir entre 20 et 30 points de vie
		}
	}
	
	private void generatePower(int niveau) {
		if(niveau == 2) { // monstre de niveau 2 (boss final)
			this.power = this.r.nextInt(6) + 15; 
			// un monstre de niveau 2 peut avoir entre 15 et 20 de force
		}
		else { // monstre de niveau 1
			this.power = this.r.nextInt(6) + 10; 
			// un monstre de niveau 1 peut avoir entre 10 et 15 de force
		}
	}
	
	public void frappe(Heros heros) {
		System.out.println("      - ["+this.getName()+"] frappe ["+heros.getName()+"] : pdv "+heros.getPdv()+" => "+(heros.getPdv() - this.power));
		heros.setPdv(heros.getPdv() - this.power);
	}
	
	public int getPdv() {
		return this.pdv;
	}

	public void setPdv(int pdv) {
		this.pdv = pdv;
	}
	
	@Override
	public String toString() {
		return "Monstre ==> [name => "+this.getName()+", pdv => " + this.pdv + ", power => " + this.power + "]";
	}

}
