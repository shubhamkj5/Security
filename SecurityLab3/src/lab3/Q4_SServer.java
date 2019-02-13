package lab3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.security.InvalidKeyException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class Q4_SServer {

	public static void main(String[] args) throws Exception {
		
		KeyGenerator kg;
		try {
			kg = KeyGenerator.getInstance("HmacMD5");	
			SecretKey sk = kg.generateKey();
			
			String encodedKey = Base64.getEncoder().encodeToString(sk.getEncoded());
			System.out.println("Encoded Key stored in file :" + encodedKey);
			FileIO.writeToFile("data/secretKey2.txt", encodedKey);
			
			String challenge = "Hey there";
			Mac mac = Mac.getInstance("HmacMD5");	
			mac.init(sk);			
			byte[] hmac = mac.doFinal(challenge.getBytes());
			
			ServerSocket ss = new ServerSocket(2000);	
			System.out.println("Server: waiting for connection...");
			Socket s = ss.accept();	
			BufferedReader inp = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintStream p = new PrintStream(s.getOutputStream());
			
				String input = inp.readLine();
				System.out.println("Hello " + input);
				
				p.println(challenge);						
		
				String clientHmac = inp.readLine();
				byte[] decodedHmac = Base64.getDecoder().decode(clientHmac.getBytes());
				
				System.out.println("Valid Hmac recieved: " + Arrays.equals(hmac, decodedHmac));
				if(Arrays.equals(hmac, decodedHmac)==true)
					p.println("Success");
				else
					p.println("Failure");
				s.close();
				ss.close();
			
		} 
		catch (InvalidKeyException e) {
				e.printStackTrace();
		}
		catch (SocketException e) {
			e.printStackTrace();
			System.out.println("Connection closed");
		}		
	}

}
