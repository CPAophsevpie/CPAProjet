package fr.viacesi.cpaimport;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Util;


public class IdentifiantBDD {

	String login;
	String mdp;	
	static boolean IdConnexion = false;
	Utilisateur util = new Utilisateur();


	
	public String lectureChampBDD(String ChampBDD, String Requete) throws SQLException{
		
		String AttributClass;

		ConnectionMySQL connecteur = new ConnectionMySQL("127.0.0.1", "cpauser", "");
		connecteur.connect();
		
		ResultSet resultSet = connecteur.execute(Requete);
		
		if(resultSet.next()){
			AttributClass = resultSet.getString(ChampBDD);
		}else {
			throw new SQLException("Login de connexion à l'application inconnu");
		}
		//Bonne pratique: fermer votre résultat
		resultSet.close();
		//Bonne pratique: fermer le connecteur
		connecteur.close();
		return AttributClass;
	}
	
	
	public boolean verifIdBdd () throws SQLException {
		
		String login = getLogin();
		String mdp = getMdp();
		if(lectureChampBDD("Login_Habilitation", "SELECT Login_Habilitation FROM salaries where"
				+ " Login_Habilitation = '"+login+"'") == login && 
				lectureChampBDD("MdP_Habilitation","SELECT Mdp_Habilitation FROM "
						+ "salaries where Mdp_Habilitation = '"+mdp+"'") == mdp ){
			IdConnexion = true;
		}
		return IdConnexion;
	}
	
	
	public String getConnexionRole()throws SQLException{
		
		String role ="";
		
		if (verifIdBdd()){
			role = lectureChampBDD("Type_Role", "SELECT Type_Role FROM role inner join salaries sal "
					+ "on id.salaries = id.role where Login_Habilitation = '"+login+"'");
		}else{
			System.out.println("Le mot de passe est incorrect");
		}
		return role;
	}
	
	
	public String getLogin(){
		Scanner in = new Scanner (System.in);
		System.out.println("Veuillez entrer votre login : ");
		String login = in.nextLine();
		return login;		
	}
	
	
	public String getMdp(){
		Scanner in = new Scanner (System.in);
		System.out.println("Veuillez entrer votre mot de passe : ");
		String mdp = in.nextLine();
		return mdp;		
	}
	
	
	
	public static void main(String[] args) {
		
		try {
			ConnectionMySQL.init();;			
		}catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			System.err.println("La librairie n'est pas disponible");
			System.exit(5);
		}

		IdentifiantBDD idbdd = new IdentifiantBDD();
		
		try {
			System.out.println(idbdd.verifIdBdd());

//			System.out.println(idbdd.getConnexionRole());
			
			
		} catch (SQLException e) {
			
			System.out.println("");
			System.err.println("ERREUR");
			e.printStackTrace();
		}
	}
}