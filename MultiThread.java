
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class MultiThread extends Thread {
	
	public Socket client;
	ArrayList<Object> objet_envoye;
	ArrayList<Object> objet_recu;
	public int i;
	

	public MultiThread(Socket client,ArrayList<Object> objet_envoye,ArrayList<Object> objet_recu,int i) {
		this.client=client;
		this.objet_envoye=objet_envoye;
		this.objet_recu=objet_recu;
		this.i=i;
	}
	
		public void run() {
		try {
			
			OutputStream out = client.getOutputStream();
			ObjectOutputStream objet_out = new ObjectOutputStream(out);
			InputStream in = client.getInputStream();
			ObjectInputStream objet_in = new ObjectInputStream(in);
			
			synchronized(objet_envoye){
	
				if(objet_envoye.isEmpty()){
					System.out.println("Thread "+i+" : Pas d'objet.");
					String vide=new String("vide");
					objet_out.writeObject(vide);
					
					client.close();
					this.interrupt();
					
				}
				else {
						Object o=objet_envoye.get(objet_envoye.size()-1);
						objet_envoye.remove(objet_envoye.get(objet_envoye.size()-1));
						objet_out.writeObject(o);
				}
			}
			
			if(!this.isInterrupted()) {
				synchronized(objet_recu) {
					
					Object o=(Object)objet_in.readObject();
					System.out.println("Thread "+i+" : Ajout de l'objet "+o.getClass().getSimpleName() + " ( " +o+" )");
					objet_recu.add(o);
					
				}
			}
			
			
			client.close();
			
			
		}catch (IOException | ClassNotFoundException e) {
			 System.err.println(e.getMessage());
			 System.out.println("deconnexion sur le thread "+i);

			} 
			
			
		
	}
}