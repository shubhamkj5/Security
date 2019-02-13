package lab3;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class Q1 {
public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
	KeyGenerator kg = KeyGenerator.getInstance("HmacSHA256");
	SecretKey sk = kg.generateKey();
	Mac mac = Mac.getInstance("HmacSHA256");
	mac.init(sk);
	byte[] result = mac.doFinal("Hi There".getBytes());
	System.out.println(result.length);

	/// Receiver
	Mac mac2 = Mac.getInstance("HmacSHA256");
	mac2.init(sk);
	byte[] result2 = mac.doFinal("Hi There".getBytes());

	System.out.println("Check: " +
	Arrays.equals(result, result2));
}
}
