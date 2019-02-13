package lab3;


import java.io.File;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class Q2_Sender {
	
	public static void main(String[] args) throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance("HmacSHA256");
		SecretKey sk = kg.generateKey();
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(sk);
		String text="";
		
		File file = new File("data/sendText.txt"); 
	    Scanner sc = new Scanner(file); 
			  
			    while (sc.hasNextLine()) {
			     text+=sc.nextLine();
			    }
		
	    byte[] hmac = mac.doFinal(text.getBytes());
	    
	 // Base64 encode a Hmac
		String encodedHmac =Base64.getEncoder().encodeToString(hmac);
		FileIO.writeToFile("data/hmac.txt", encodedHmac);
		
		// Base64 encode a secret key
		Object encodedKey =Base64.getEncoder().encodeToString(sk.getEncoded());
		FileIO.writeToFile("data/secretKey.txt", encodedKey);
		
		System.out.println("Success");
		
				
			
	}
}
