package lab3;

import java.io.File;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Q3_Receiver {
  public static void main(String[] args) throws Exception {
	
	String encodedKey = (String) FileIO.readFromFile("data/secretKey.txt");
	byte[] decodedKey =Base64.getDecoder().decode(encodedKey);
    SecretKey sk = new SecretKeySpec(decodedKey, 0,decodedKey.length,"HmacSHA256");
	
    String encodedHmac = (String) FileIO.readFromFile("data/hmac.txt");
   
    // Base64 decode a HMAC
    byte[] decodedHmac = Base64.getDecoder().decode(encodedHmac);
    
    String text="";
    File file = new File("data/sendText.txt"); 
    Scanner sc = new Scanner(file); 
		  
    while (sc.hasNextLine()) {
     text+=sc.nextLine();
    }
	
		  
	Mac mac2 = Mac.getInstance("HmacSHA256");
	mac2.init(sk);
	byte[] result2 = mac2.doFinal(text.getBytes());

	System.out.println("Check: " +
	Arrays.equals(decodedHmac, result2));
 }
}
