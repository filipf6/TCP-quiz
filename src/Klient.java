
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Klient {

	public static void main(String[] args) {
		
		
		try 
		{
			Scanner keyboard = new Scanner(System.in);
			Socket gniazdo = new Socket("localhost",2221);
			BufferedReader in = new BufferedReader(new InputStreamReader(gniazdo.getInputStream()));
			PrintWriter out = new PrintWriter(gniazdo.getOutputStream(), true);
			String line;
			String msg;
			
			while(true)
			{
				
				while(true)
				{
					if((line = in.readLine()).equals("end"))
						break;
					if(line.equals("KONIEC"))
						return;
					System.out.println(line);
						
				}
				
				msg = keyboard.nextLine();
				out.println(msg);
				
			}
			
		}
		
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			System.out.println("program zakoñczony");
			//e.printStackTrace();
		}

	}

}
