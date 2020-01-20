package md;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class M2messageDigest {
  public static void main(String[] args) {

    String password = "12345";
    // also try long string. change 1 char in string and see how much the
    // message digest changes.
    MessageDigest algorithm = null;
    try {
      algorithm = MessageDigest.getInstance("SHA-256");
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
