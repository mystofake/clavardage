import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


//La classe Message permet de recueillir les messages à envoyer ainsi que toutes les métadonnées qui les accompagent.
//Elle est serializable puisqu'elle sera envoyée à chaque nouveau message envoyé.

public class Message implements Serializable {

  private String pdu;
  private User userDest;
  private User userOri;
  private String dateEnvoi;

  public Message() {}
  
  
  public Message(String pdu, User userDest, User userOri)
  {
	  this.pdu=pdu;
	  this.userDest = userDest;
	  this.userOri = userOri;
	  this.dateEnvoi = recupDate();
  }
  
  
  public void SendMessage (ObjectOutputStream out)	
 {
	  	this.dateEnvoi=recupDate();
	  	try {
	  		out.writeObject(this);
	  	}
		
	  		catch(IOException e)
	  		{
	  			System.out.println("ERROR FUNCTION : SendMessage");
	  		}
 }
	
	

  public String recupDate()
  {
	  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String reportDate = dateFormat.format(date);
		return(reportDate);
  }
  
  public static Message recupMessage(ObjectInputStream in)
  {	  
	  Message mess = null;
	  try {

		while (mess == null)
			mess = (Message) in.readObject();
		//add to discussion
		}
	catch ( IOException e )
	{
		System.out.println("ERROR FUNCTION : recupMessage - IOException");
	}
	catch (ClassNotFoundException e)
	  {
		System.out.println("ERROR FUNCTION : recupMessage - ClassnotfounException");
	  }
  	return(mess);
  }
  
  
  public String toString()
  {
	  return("|" + this.dateEnvoi + "| " + this.userOri.getPseudo()+ " : " + this.pdu);
  }
}
