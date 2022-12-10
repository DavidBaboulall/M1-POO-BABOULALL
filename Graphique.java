import  javax.swing.*;
import  java.awt.*;
import  java.awt.event.*;
import java.util.*;
  

public class Graphique extends JFrame implements ActionListener
{
	JFrame frame;
	JTextField text;
    
    public Graphique(String element)
    {

        super();
        
		frame = new JFrame("Application");
    
        JPanel  pan=new JPanel();
        pan.setLayout(new BorderLayout());
        
       	JButton btn = new JButton("Valider");
       

		btn.addActionListener(this);
        
        pan.add(btn,BorderLayout.CENTER);
        

        
        setContentPane(pan);
        pack(); 

	 	

	    JLabel label = new JLabel(element);
	    label.setBounds(100,80,200,28);

	    
	    
	    text = new JTextField();
	    text.setBounds(200,80,200,28);



	    btn.setBounds(250,130,100,28);
	    
	    frame.add(btn);
	    frame.add(label);    
	    frame.add(text);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(500,500);
	    frame.setLayout(null);
	    frame.setVisible(true);




    }
    

    // public  void    actionPerformed(ActionEvent e)
    // {

    // 	String valeur="";
	
	// 	 valeur = text.getText();

    // 	System.out.println(valeur);


    // }



    public  void    actionPerformed(ActionEvent e)
    {

    	int valeur=0;

		
			try 
			{
				
		    	valeur = Integer.parseInt(text.getText());
				
			}
			catch(final NumberFormatException  except) 
			{

				JOptionPane.showMessageDialog(frame, "Entrez un entier" );

			}

    	System.out.println(valeur);


    }
    
    public  static  void    main(String args[])
    {
        new Graphique("Age ");
    }
}