import java.io.*;
import java.net.*;

public class ThreadAffichage extends Thread{
	
	private Socket mySocket;
    private volatile boolean running = true;
    public NetworkInterface nw;
    


	public ThreadAffichage(Socket mySocket, NetworkInterface nw)
	{
		this.mySocket = mySocket;
		this.nw = nw;
		start();
		
	}
    public void terminate() {
        running = false;
    }

	
	public void run()
	{
		
		try {
			
			System.out.println("THREAD AFFICHAGE LANCE");

			while(running)
			{			

					Message messtest = Message.recupMessage(this.nw.in2);
					System.out.println(messtest);
					if(messtest.getDeco())
					{
						running = false;
						this.nw.CW.writeDecoMessage(messtest);
					}
					else
					{
						this.nw.CW.writeReceivedMessage(messtest);
					}
			

			}
		}
		catch (Exception e){
			
			
			
			System.out.println("Erreur Thread Affichage");
		}
		
	}
}
