package Main;

import java.util.Random;

public class Heros extends Personnage {
	
	private Random r = new Random();
	private int pdv;
	private int power;
	private int butin = 0;
	
	public Heros(String name) {
		super(name);
		this.generatePdv();
		this.generatePower();
	}
	
	private void generatePdv() {
		this.pdv = this.r.nextInt(11) + 90;
		// un heros peut avoir entre 90 et 100 points de vie
	}
	
	private void generatePower() {
		this.power = this.r.nextInt(6) + 15; 
		// un heros peut avoir entre 15 et 20 de force
	}
	
	public void frappe(Monstre monstre) {
		System.out.println("      - ["+this.getName()+"] frappe ["+monstre.getName()+"] : pdv "+monstre.getPdv()+" => "+(monstre.getPdv() - this.power));
		monstre.setPdv(monstre.getPdv() - this.power);
	}
	
	public void addButin(int buttin) {
		this.butin += buttin;
	}
	
	public int getPower() {
		return this.power;
	}
	
	public int getButin() {
		return this.butin;
	}
	
	public void setButin(int butin) {
		this.butin = butin;
	}
	
	public int getPdv() {
		return this.pdv;
	}

	public void setPdv(int pdv) {
		this.pdv = pdv;
	}

	@Override
	public String toString() {
		return "Heros ==> [name => "+this.getName()+", pdv => " + this.pdv + ", power => " + this.power + "]";
	}
	

}
