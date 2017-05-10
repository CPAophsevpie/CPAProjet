package fr.viacesi.cpaimport;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Util;


public class IdentifiantBDD {

	String login;
	static boolean IdConnexion = false;
	
	
	
	/**
	 * Methode g�n�rique pour r�cup�rer un champ de la BDD
	 * @param ChampBDD
	 * @param Requete
	 * @return valeur du ChampBDD
	 * @throws SQLException
	 */
	public String lectureChampBDD(String ChampBDD, String Requete) throws SQLException{
		
		String valeur = null;
		ConnectionMySQL connecteur = new ConnectionMySQL("127.0.0.1", "cpauser", "");
		connecteur.connect();
		ResultSet resultSet = connecteur.execute(Requete);
		
		if(resultSet.next()){
			valeur = resultSet.getString(ChampBDD);
		}
		resultSet.close();
		connecteur.close();
		return valeur;
	}
	
	
	/**
	 * Identification utilisateur
	 * @return boolean de connexion
	 * @throws SQLException
	 */
	public boolean verifIdBdd () throws SQLException {
		
		login = getLogin();
		String mdp = getMdp();
		
		if(lectureChampBDD("Login_Habilitation", "SELECT Login_Habilitation FROM salaries where"
				+ " Login_Habilitation = '"+login+"'") != null && 
				lectureChampBDD("MdP_Habilitation","SELECT Mdp_Habilitation FROM "
						+ "salaries where Mdp_Habilitation = '"+mdp+"'") != null ){
			return IdConnexion = true;
		}
		else{
			System.out.println("L'identifiant et/ou le mot de passe est incorrect");
			return IdConnexion;
		}
	}
	
	
	/**
	 * Instanciation d'un utilisateur
	 * @throws SQLException
	 */
	public void getUtilisateur()throws SQLException{
				
		Utilisateur util = new Utilisateur();

		if (verifIdBdd()){
			util.role = lectureChampBDD("r.Type_Role", "SELECT r.Type_Role from role r "
					+ "inner join salaries s on s.ID_Salarie = r.ID_Role "
					+ "where Login_Habilitation = '"+login+"'");
			util.nom = lectureChampBDD("salaries.Nom_Salarie", "SELECT salaries.Nom_Salarie from salaries"
					+ " where salaries.Login_Habilitation = '"+login+"'");
			util.prenom = lectureChampBDD("salaries.Prenom_Salarie", "SELECT salaries.Prenom_Salarie from salaries"
					+ " where salaries.Login_Habilitation = '"+login+"'");
			System.out.println("Bonjour " + util.role + " " + util.prenom + " " + util.nom);
			}
	}
	
	
	/**
	 * Demande du login
	 * @return login
	 */
	public String getLogin(){
		Scanner in = new Scanner (System.in);
		System.out.println("Veuillez entrer votre login : ");
		String login = in.nextLine();
		return login;		
	}
	
	
	/**
	 * Demande du mot de passe
	 * @return mot de passe
	 */
	public String getMdp(){
		Scanner in = new Scanner (System.in);
		System.out.println("Veuillez entrer votre mot de passe : ");
		String mdp = in.nextLine();
		return mdp;		
	}
}