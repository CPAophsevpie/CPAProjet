package fr.viacesi.cpaimport;

import java.sql.SQLException;



public class Main {

	public static void main(String[] args) {
		
		try {
			ConnectionMySQL.init();;			
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			System.err.println("La librairie n'est pas disponible");
			System.exit(5);
		}
	
		IdentifiantBDD idbdd = new IdentifiantBDD();
		Menu fct = new Menu();
		
		try {
			idbdd.getUtilisateur();
			fct.gererProgramme();
			
			System.out.println(IdentifiantBDD.IdConnexion);
			
		} catch (SQLException e) {
			System.out.println("");
			System.err.println("ERREUR : impossible d'accéder à la base de données");
			e.printStackTrace();
		}
	}
}