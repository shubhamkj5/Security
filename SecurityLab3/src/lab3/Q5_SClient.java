package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Q5_SClient {
public static void main(String[] args) throws IOException {
	try 
	{
		String encodedKey = (String) FileIO.readFromFile("data/secretKey2.txt");
		byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
		SecretKey sk = new SecretKeySpec(decodedKey, 0, decodedKey.length,"HmacSHA256");
		Mac mac = Mac.getInstance("HmacMD5");	
		mac.init(sk);	
		
		InetAddress inet = InetAddress.getByName("localhost");
		Socket s = new Socket(inet, 2000);		
		PrintStream out = new PrintStream(s.getOutputStream());
		BufferedReader bfr = new BufferedReader(new InputStreamReader(s.getInputStream()));
	
		out.println("AIT");
		
		String input = bfr.readLine();
		System.out.println("Message from server: "+input);
				
		byte[] newHmac = mac.doFinal(input.getBytes());
		System.out.println("Hmac length: "+newHmac.length);
		String encodedHmac = Base64.getEncoder().encodeToString(newHmac);
		out.println(encodedHmac);
		
		String input2 = bfr.readLine();
		System.out.println("Message from server: "+input2);

		s.close();
	}
	catch (Exception e) {
		System.out.println("Connection closed");
	}
}
}
