import java.io.Serializable;

public class Eleve implements Serializable 
{
	


	private static final long serialVersionUID = 1L;
	public String nom;
	public String prenom;
	public String diplome;
	
	public Eleve(String nom, String prenom, String diplome )
	{
		this.nom=nom;
		this.prenom=prenom;
		this.diplome = diplome;
	}


	public Eleve() 
	{


		this.nom="dupon";
		this.prenom="jean";
		this.diplome="Licence";
	
		
	}

	
}