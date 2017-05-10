package fr.viacesi.cpaimport;

import java.util.Scanner;

public class Menu {
	
	private Scanner scanner = new Scanner(System.in);

	
	public static final int CHOIX_IMPORT = 1;
	public static final int CHOIX_EXPORT = 2;
	public static final int CHOIX_EXIT = 0;
	
	
	public void gererProgramme(){
		int choix = -1;
		while(choix != 0){
			afficherMenu();
			choix = getValeur("\nEntrez votre choix : ");
			switch(choix){
			case CHOIX_IMPORT:
				System.out.println("\n**MENU IMPORT**");
				break;
			case CHOIX_EXPORT:
				System.out.println("\n**MENU EXPORT**");
				break;
			case CHOIX_EXIT:
	            System.exit(0);
				break;	
			}
		}
		scanner.close();
	}
	
	
	public void afficherMenu(){
		System.out.println("\nUTILITAIRE CPA");
		System.out.println("1. Importer des données");
		System.out.println("2. Exporter des données");
		System.out.println("0. Quitter le programme");
	}
	
	
	public int getValeur(String message){
		System.out.print(message);
		return scanner.nextInt();
	}
}


