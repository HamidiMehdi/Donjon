package Jeux;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * @author mehdi
 *
 */
public class FindNumber {
	
	private ArrayList<Integer> code_secret = new ArrayList<Integer>();
	private ArrayList<Integer> code_user   = new ArrayList<Integer>();

	/**
	 * 
	 */
	public FindNumber() {
		this.generateCode();
		System.out.println(this.getCodeSecret());
		this.play();
	}
	
	private void play() {
		Scanner sc = new Scanner(System.in);
		for(int i=1; i<9; i++) {
	    	System.out.println("Essai " +i+" : ");
    		if(this.manageCodeUser(sc.next()) == 500) {
    			System.out.println("Erreur - Saisissez un nombre réel");
		    	continue;
    		}
    		if( this.code_user.size() != 4 ) {
		    	System.out.println("Erreur - Saisissez un nombre a 4 chiffres");
		    	continue;
		    }
    		
		    if(this.judgeResult() == 200) {
		    	System.out.println("Code trouvé, Félicitation");
		    	break;
		    }
		}
	}
	
	private int judgeResult() {
		int finish = 200 ;
		for(int i = 0; i<this.code_user.size(); i++) {
			if(this.code_user.get(i) == this.code_secret.get(i)) {
				System.out.print("1");
			}
			else {
				// Si il existe autre part, et qu'a cette meme place il a pas été prit
				//  et l'autre est pas bon je met "." + sauvegarde
				//
				// Si il existe pas autre part, "x" gg
				//
				finish = 500;
				System.out.print("x");
			}
		}
		System.out.println(" ");
		return finish;
	}
	
	private int manageCodeUser(String code_user) {
		this.code_user.clear();
		String tab[] = code_user.split("");
		try{
			for(int i = 0; i < tab.length; i++) {
				this.code_user.add( Integer.parseInt(tab[i]) );
			}
			return 200;
		}
		catch (NumberFormatException e){
		      return 500;
		}
	}
	
	private void generateCode() {
		Random r = new Random();
		for(int i = 0; i < 4; i++) {
			this.code_secret.add(r.nextInt(9 - 0));
		}
	}
	
	private String getCodeSecret() {
		String str_code = "";
		for(int n : this.code_secret) {
			str_code += String.valueOf(n);
		}
		return "[" + str_code + "]";
	}
}
