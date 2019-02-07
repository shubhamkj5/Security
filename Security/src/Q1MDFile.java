import java.security.MessageDigest;
import java.util.Scanner;

public class Q1MDFile {
   public static void main(String args[]) throws Exception{
     
    String password = "12345";
 MessageDigest algorithm = null;
 try {
 algorithm = MessageDigest.getInstance("MD5");
 } catch (NoSuchAlgorithmException e) {
 e.printStackTrace();
 }
 algorithm.reset();
 algorithm.update(password.getBytes());
 byte[] messageDigest = algorithm.digest();
	   System.out.println("length " + messageDigest.length);

 String encodedDigest = Base64.getEncoder().encodeToString(messageDigest);;
 System.out.println("Base64 encoded message digest " + encodedDigest);
   }
}
