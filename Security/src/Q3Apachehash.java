import org.apache.commons.codec.digest.DigestUtils;

public class Q3Apachehash {
	public void givenPassword_whenHashingUsingCommons_thenVerifying()  {
	   
	}
public static void main(String[] args) {
	  String password = "hello123";
      String digest = DigestUtils.sha256Hex(password);

   
      System.out.println("Password        = " + password);
      System.out.println("Password Digest = " + digest);
      System.out.println("Length          = " + digest.length());
}
}
