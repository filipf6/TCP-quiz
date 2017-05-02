import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class SerwerThread extends Thread {
	
	Socket gniazdo;
	ArrayList<Pytanie> listaPytan;
	BufferedReader in;
	PrintWriter out;
	
	public SerwerThread(Socket gniazdo, ArrayList<Pytanie> listaPytan)
	{
		this.gniazdo = gniazdo;
		this.listaPytan = listaPytan;
	}
	
	public synchronized void run()
	{
		try 
		{
			in = new BufferedReader(new InputStreamReader(gniazdo.getInputStream()));
			out = new PrintWriter(gniazdo.getOutputStream());
			
			Iterator<Pytanie> iterator = listaPytan.iterator();
			Pytanie aktualnePytanie=null;
			String odpowiedz=null;
			String linia=null;
			boolean flaga = false;

			while(true)
			{
				
				if(iterator.hasNext() == false)
				{
					out.println("KONIEC");
					out.flush();
					break;
				}
				
				aktualnePytanie = iterator.next();
				out.print(aktualnePytanie.getPytanie());
				out.println("end");
				out.flush();
				
				
				odpowiedz = in.readLine();
				
				if(aktualnePytanie.getPoprawnaOdpowiedz() == odpowiedz.charAt(0))
					out.println("Bardzo dobrze!");
				else
					out.println("èLE, POPRAWNA ODPOWIEDZ, TO :" + aktualnePytanie.getPoprawnaOdpowiedz());
				
			}
		} 

		
		catch (Exception e) 
		{
			System.out.println("koniec");
		}
		
		
	}

}
