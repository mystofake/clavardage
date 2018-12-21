import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Le LoadBalancer ( port 1234 ) recueille toutes les demandes de debut de chat et attribut au client "distant"
//  et au serveur local un num�ro de port sur lequel se rejoindre et op�rer.

public class LoadBalancer extends Thread {

	int port;
	List <Serveur> ListServer = new ArrayList<>();
	Controler c;
	
	
	public LoadBalancer(Controler c)
	{
			this.c=c;
			start();
		
	}
	
	public void run() 
	{
	
		int port = 1300;
	try {
		
		ServerSocket LoadBalancer = new ServerSocket(1234); // Serveur d'inizialition 
		ExecutorService executor = Executors.newCachedThreadPool();
		System.out.println("LoadBalancer : LAUNCHED ON PORT 1234");
		
		while(true)
			{
				port +=1;
				Socket MySocket = LoadBalancer.accept();
				System.out.println("LOADBALANCER - Connexion recue");

				Serveur s1 = new Serveur(port, c);
				System.out.println("End");

				ListServer.add(s1); 
				PrintWriter out = NetworkFunctions.outStream(MySocket);
				out.println(port);
				System.out.println("End");
				
				MySocket.close();
				
			}
		        	
		}
		
		
	catch (IOException e) {
		
		System.out.println("ERROR FUNCTION : LoadBalancer");
		}
	
	}
}
