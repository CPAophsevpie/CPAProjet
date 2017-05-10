package fr.viacesi.cpaimport;

public class Utilisateur {

    public String nom;
    public String prenom; 
    public String role;
    public Utilisateur(){
    	
    }
	
	public Utilisateur (String nom, String prenom, String role ){
		this.nom = nom;
		this.prenom = prenom;
		this.role = role;
	 }

	
	public String getNom(){
		return nom;		
	}
	
	
	public String getPrenom(){
		return prenom;		
	}
	
	
	public String getRole(){
		return role;		
	}
}