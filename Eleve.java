import java.io.Serializable;

public class Eleve implements Serializable 
{

	private static final long serialVersionUID = 1L;
	public String nom;
	public String prenom;
	public int age;
	public boolean celibataire; 
	public String diplome;
	
	public Eleve(String nom, String prenom, String diplome , int age, boolean celibataire  )
	{
		this.nom=nom;
		this.prenom=prenom;
		this.diplome = diplome;
		this.age = age;
		this.celibataire =celibataire;
	}


	public Eleve() 
	{


		nom="dupon";
		prenom="jean";
		age =18;
		celibataire =true;
		diplome="Licence";
	
		
	}

	public String toString() 
	{
		return  "age : "+age+", nom : " +nom +", prenom : "+prenom+", diplome:"+diplome+", celibataire :"+celibataire;
	}


	
}