package Main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Lancement du jeu...");
		Scanner sc = new Scanner(System.in);
		System.out.println("Voulez vous entrer dans un donjon ? (y/n)");
		if(sc.next().equals("y")) {
			new Donjon();
		}
		else{
			System.out.println("Les donjons ne sont pas faits pour les trouillards...");
		}
	}

}
