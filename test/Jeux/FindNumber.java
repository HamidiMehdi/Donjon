package Jeux;

import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author mehdi
 *
 */
public class FindNumber {
	
	private ArrayList<Integer> code_secret = new ArrayList<Integer>();
	private ArrayList<Integer> code_user   = new ArrayList<Integer>();
	private Scanner sc = new Scanner(System.in);
	private File file = new File("C:/Users/mehdi/eclipse-workspace/fileData.txt");

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
		this.fileExist();
		FileWriter fw;
		Date start = new Date();
		Date end;
		Boolean succes = false;
		try {
			fw = new FileWriter(this.file, true);
			fw.write("\n");
			fw.write("Code secret ===> " + this.getCodeSecret() + "\n");
			for(int i=1; i<9; i++) {
		    	System.out.println("Essai " +i+" : ");
		    	String code_user = this.sc.next();
		    	fw.write("Essai "+i+ " ===> " + code_user + "\n");
	    		if(!this.manageCodeUser(code_user)) {
	    			System.out.println("Erreur - Saisissez un nombre r�el");
			    	continue;
	    		}
	    		if(this.code_user.size() != 4) {
			    	System.out.println("Erreur - Saisissez un nombre a 4 chiffres");
			    	continue;
			    }
			    if(this.judgeResult()) {
			    	succes = true;
			    	end = new Date();
			    	System.out.println("Code trouv� en "+i+" essai(s)/"+this.getTimeSecond(start, end)+" seconde(s), F�licitation");
			    	fw.write("Jeu reussi en "+i+" essai(s)/"+this.getTimeSecond(start, end)+" seconde(s)\n");
			    	break;
			    }
			}
			if(!succes) {
		    	end = new Date();
		    	fw.write("Jeu non reussi en 8 essai(s)/"+this.getTimeSecond(start, end)+" seconde(s)\n");
			}
			fw.write("--------------------------------------------");
	        fw.close() ;
		} catch (IOException e) {
			System.out.println("Impossible d'�crire dans le fichier");
			e.printStackTrace();
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
	
	private long getTimeSecond(Date start, Date end) {
		return (end.getTime() / 1000) - (start.getTime() / 1000);
	}
	
	private boolean fileExist() {
		try {
		    this.file.createNewFile();
		    return true;
	    } catch (IOException e) {
	    	System.out.println("Erreur creation du ficher ==> " + e.getMessage());
		    return false;
		}
	}
}