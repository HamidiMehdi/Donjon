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
	private Scanner sc = new Scanner(System.in);

	/**
	 * 
	 */
	public FindNumber() {
		this.generateCode();
		System.out.println(this.getCodeSecret());
		this.play();
		this.replay();
	}
	
	private void play() {
		for(int i=1; i<9; i++) {
	    	System.out.println("Essai " +i+" : ");
    		if(!this.manageCodeUser(this.sc.next())) {
    			System.out.println("Erreur - Saisissez un nombre réel");
		    	continue;
    		}
    		if(this.code_user.size() != 4) {
		    	System.out.println("Erreur - Saisissez un nombre a 4 chiffres");
		    	continue;
		    }
    		
		    if(this.judgeResult()) {
		    	System.out.println("Code trouvé, Félicitation");
		    	break;
		    }
		}
	}
	
	private boolean judgeResult() {
		boolean finish = true ;
		for(int i = 0; i<this.code_user.size(); i++) {
			if(this.code_user.get(i) == this.code_secret.get(i)) {
				System.out.print("1");
			}
			else {
				if(this.verifIfExist(this.code_user.get(i))) {
					System.out.print(".");
				}
				else {
					System.out.print("x");
				}
				finish = false;
			}
		}
		System.out.println(" ");
		return finish;
	}
	
	private boolean verifIfExist(int num) {
		boolean exist = false;
		for(int i = 0; i<this.code_secret.size(); i++) {
			if( this.code_secret.get(i) == num &&  this.code_secret.get(i) != this.code_user.get(i) ) {
				exist = true;
			}
		}
		return  exist;
	}
	
	private boolean manageCodeUser(String code_user) {
		this.code_user.clear();
		String tab[] = code_user.split("");
		try{
			for(int i = 0; i < tab.length; i++) {
				this.code_user.add( Integer.parseInt(tab[i]) );
			}
			return true;
		}
		catch (NumberFormatException e){
		      return false;
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
	
	private void replay() {
		System.out.println("Voulez vous rejouer (y/n)");
		if(sc.next().equals("y")) {
			System.out.println("---------------------");
			this.code_secret.clear();
			this.generateCode();
			System.out.println(this.getCodeSecret());
			this.play();
			this.replay();
		}
	}
}
