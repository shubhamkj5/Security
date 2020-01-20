package md;
import java.util.Base64;

public class M1base64Encode {
  public static void main(String[] args) {

     String s = "qwertyu" ;
     byte[] sBytes = s.getBytes() ;
     String encodedString = Base64.getEncoder().encodeToString(sBytes);
     System.out.println("s is: " + s + "  Encoded: " + encodedString);

     byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
     System.out.println("Encoded: " + encodedString + 
                          "  Decoded: " + new String(decodedBytes));

  }
}
