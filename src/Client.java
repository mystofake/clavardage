import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client extends NetworkInterface {

	private User userDest;
	//public Controler c;
	
public Client(User userDest, Controler c)
{
	super(c);
	//this.c = c;
	this.CW = new ChatWindow(this);
	this.CW.createAndShowGUI();
	this.userDest = userDest;
	this.BeginChat(userDest.getAddress());
}



public void BeginChat(InetAddress AddServer) {
	try {

	// Réception du port sur lequel on va communiquer
	Socket MySocket = new Socket(AddServer,1234);
	BufferedReader in = NetworkFunctions.inStream(MySocket);
	String input = in.readLine();
	System.out.println("test");
	int port = Integer.valueOf(input);
	MySocket.close();

	// Creation d'un chat client sur le port recu
	Socket MySocket2 = new Socket(AddServer,port);
	System.out.println("ChatClient : CONNECTED ON PORT "+ port);
	
	this.out2 = NetworkFunctions.outObj(MySocket2);
	this.in2 = NetworkFunctions.inObj(MySocket2);
	
	ThreadAffichage t_aff = new ThreadAffichage(MySocket2, this);
	
	
}
	catch (IOException e)
	{
		
			System.out.println("BeginChat : ERREUR !");
		
		
	}
	
}	
	

}