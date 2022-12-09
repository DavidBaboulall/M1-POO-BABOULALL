import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.io.IOException;

public class MultiThread extends Thread 
{


		private Socket client;
		private ObjectOutputStream objet_out;
    	private ObjectInputStream objet_in;


		ArrayList<Object> objet_envoye;
		ArrayList<Object> objet_recu;



    public MultiThread(Socket client, ArrayList<Object> objet_envoye, ArrayList<Object> objet_recu) throws IOException
    {
    	this.client = client;
    	this.objet_recu = objet_recu;
    	this.objet_envoye = objet_envoye;

    }



    public void run()
    {


		try{

			OutputStream out = client.getOutputStream();
			ObjectOutputStream objet_out = new ObjectOutputStream(out);
			InputStream in = client.getInputStream();
			ObjectInputStream objet_in = new ObjectInputStream(in);


			synchronized(objet_envoye)
			{

				if (  objet_envoye.isEmpty())
	            {
	                synchronized (objet_envoye)
	                {

	                	client.close();
	                    this.interrupt();
	                    
	                }
	            }

	            else
	            {
	            	Object objet = objet_envoye.get(objet_envoye.size() -1);
	                objet_envoye.remove(objet_envoye.get(objet_envoye.size() -1));
	                objet_out.writeObject(objet);
	            }


	        }

            if (!this.isInterrupted())
            {
                Object objet = objet_in.readObject();
                synchronized (objet_recu)
                {
                    System.out.println( objet + " ajouté");
                    objet_recu.add(objet);
                }

            }



			client.close();

		}		
		catch (IOException e) 
		{
            //throw new RuntimeException(e);
        } 
        catch (ClassNotFoundException e) 
        {
            throw new RuntimeException(e);
        

		}


    }

}	


