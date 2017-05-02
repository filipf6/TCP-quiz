import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



public class Serwer {
	
	public ArrayList<Pytanie> inicjalizacjaBazyPytan()
	{
		ArrayList<Pytanie> listaPytan = new ArrayList<Pytanie>();
		
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader("bazaPytan.txt"));
			String linia;
			String pytanie = "";
			
			while(!(br.readLine().equals("end")))
			{
				
				pytanie = "";
				for(int i=0; i<5; i++)
				{
					pytanie += br.readLine()+'\n';
					
				}
				
				listaPytan.add(new Pytanie(pytanie, br.readLine().charAt(0)));
			}

		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Plik nie zosta³ znaleziony");
		} 
		catch (IOException e) 
		{
			System.out.println("Coœ jest nie tak z plikiem");
			e.printStackTrace();
		}
		return listaPytan;
		
	}

	public static void main(String[] args) {
		try 
		{
			Serwer serwer = new Serwer();
			InetSocketAddress isa = new InetSocketAddress("localhost", 2221);
			ServerSocket serverSocket =new ServerSocket();
			serverSocket.bind(isa);
		
			
			while(true)
			{
				Socket gniazdo = serverSocket.accept();
				(new SerwerThread(gniazdo, serwer.inicjalizacjaBazyPytan())).start();
				
			}

		}

		
		catch (IOException e) 
		{
		
			e.printStackTrace();
		}

	}

}