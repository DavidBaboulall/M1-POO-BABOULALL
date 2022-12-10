import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.*;
import java.lang.reflect.Field;
public class Client {
	
	
	public static void input_client(Object o) 
	{
		
		Scanner sc=new Scanner(System.in);
		
		ArrayList<String> liste_type = new ArrayList<String>(Arrays.asList("String","int","boolean"));
		Object valeur=null;
		for(Field f :o.getClass().getFields()) 
		{
	  		System.out.println("- "+ f.getType().getSimpleName()+" : "+f.getName()+"\n");
	  		if(liste_type.contains(f.getType().getSimpleName())) 
	  		{
	  			
				
				System.out.println("Entrez "+f.getName()+" :\n"); 
				boolean error_input;


		  		switch (f.getType().getSimpleName()) 
		  		{
					case "String":
						do {
							error_input=false;
							try {
								
								valeur=sc.nextLine();
								
							}catch(InputMismatchException e) {
								error_input=true;
								sc.nextLine();
								System.out.println("Saisie incorrecte.");
							}
						}while(error_input);
						
						
						break;
						
					case "int":
						do {
							error_input=false;
							try {
								
								valeur=sc.nextInt();
							}catch(InputMismatchException e) {
								error_input=true;
								sc.nextLine();
								System.out.println("Entrez un entier");
							}
						}while(error_input);
						
						break;
					case "boolean":

						do {
							error_input=false;
							try {

								System.out.println(f.getName()+" : true ou false ? ");
								valeur=sc.nextBoolean();
							}catch(InputMismatchException e) {
								error_input=true;
								sc.nextLine();
								System.out.println("Entrez \"true\" ou \"false\".");
							}
						}while(error_input);
						break;
				}
		  		try {
					f.set(o, valeur);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
		  		
			 }
	  		else
	  		{
				 try {
						input_client(f.get(o));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
			}	
	  	}
		 
	}
	public static void main(String[] args) 
	{
		
		Socket client=null;
		int port = 50050;

		
		try {
			
			System.out.println("Tentative de connexion");
			client=new Socket("localhost",port); 
			
			OutputStream out = client.getOutputStream();
		 	ObjectOutputStream objOut = new ObjectOutputStream(out);
			InputStream in = client.getInputStream();
		  	ObjectInputStream objIn = new ObjectInputStream(in);
		 	
	 		Object o=(Object)objIn.readObject();

	 		if(o instanceof String && o.equals("vide")) 
	 		{
	 			System.out.println("plus d'objets");
	 			client.close();
	 		}
	 		else 
	 		{

			  	System.out.println("Objet \nClasse : "+o.getClass().getSimpleName());
			  	System.out.println("Liste des Attributs :");
			  	
			  	input_client(o);
			  	
	 			
	 			
			  	System.out.println("Objet envoyee");
				objOut.writeObject(o);
				client.close();
	 		}

			  	
		} 
		catch (IOException e) 
		{
			System.err.println("server non disponible  \n"+e);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IllegalArgumentException e) 
		{
		
			e.printStackTrace();
		} 
	}
}