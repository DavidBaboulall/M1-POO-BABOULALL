import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server  {
	
	private static ArrayList<Object> objet_envoye=new ArrayList<Object>();
	private static ArrayList<Object> objet_recu=new ArrayList<Object>();
	
	

	public static void main(String[] args) 
	{
		for(int i=0;i<5;i++) 
		{
			Eleve eleve = new Eleve();
			objet_envoye.add(eleve);
		
		}
		ServerSocket socket_server;
		

		try 
		{
			int port = 50050;

			int i=1;

			socket_server=new ServerSocket(port);
			while (true) 
			{
				
				System.out.println("Serveur disponible");
				Socket client=socket_server.accept();
				System.out.println("Connexion d'un client");
				MultiThread thread=new MultiThread(client,objet_envoye,objet_recu,i);
				System.out.println("thread "+i+ " en cours ...");
				thread.start();
				i++;
			}
		}
		catch (IOException e) {
		 System.err.println(e.getMessage());
		 
		}
			
		
		
			

	}

}