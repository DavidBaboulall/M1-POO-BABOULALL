import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.lang.reflect.Field;
import java.util.Scanner;

public class Client  
{
    private static int port = 5005;


    public static void main(String[] args) 
    {




        try
        {

            Socket client = new Socket("localhost",port);

            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            ObjectInputStream objet_in = new ObjectInputStream(in);
            ObjectOutputStream objet_out = new ObjectOutputStream(out);

            Object objet = objet_in.readObject();

            if(objet instanceof String && objet.equals("deconnexion"))
            {
                client.close();
            }


            Scanner sc=new Scanner(System.in);

            Object val=null;

            for(Field field :objet.getClass().getFields())
            {


                System.out.println(field.getType().getSimpleName()+" : "+field.getName()+"\n");
                    
                    
                    System.out.println("Entrer le "+field.getName()+"\n"); 
                                    

                    val =sc.nextLine();


                    field.set(objet, val);
                

            }



    
            objet_out.writeObject(objet);


            client.close();


        }




        catch (IOException e) 
        {
            System.err.println(e.getMessage());
        } 
        catch (ClassNotFoundException e) 
        {
            e.printStackTrace();
        } 
        catch (IllegalArgumentException e) 
        {
            e.printStackTrace();
        } 

        catch (IllegalAccessException e) 
        {
            throw new RuntimeException(e);
        }

        
    }

}