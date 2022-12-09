import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.IOException;



public class Server  {

	private static int port = 5005;
	public static ArrayList<Object> objet_envoye = new ArrayList<Object>();
    public static ArrayList<Object> objet_recu = new ArrayList<Object>();


  	public static void main(String[] args)
    {



    	for(int i=0;i<3;i++) 
    	{
    		Eleve eleve = new Eleve();
			objet_envoye.add(eleve);		
		}

		Socket client;

		ServerSocket server;



		try
		{
			while(true)
			{

				server = new ServerSocket(port);
				client=server.accept();
				
				MultiThread thread =new MultiThread(client,objet_envoye,objet_recu);
				
				thread.start();



			}

		}
		catch( IOException e)
		{
			//System.err.println(e.getMessage());
		}

	}
}