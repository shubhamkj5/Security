import java.util.Arrays;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class H1hmacEx {
   public static void main(String[] args) throws Exception {
      // Generate secret key for HMAC-MD5
      KeyGenerator kg = KeyGenerator.getInstance("HmacSHA256");
      SecretKey sk = kg.generateKey();

      // Get instance of Mac object implementing HMAC-MD5, and
      // initialize it with the above secret key
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(sk);
      byte[] hmacSignature = mac.doFinal("Hi There".getBytes());
      System.out.println(hmacSignature.length);

      /// Receiver
      Mac mac2 = Mac.getInstance("HmacSHA256");
      mac2.init(sk);
      byte[] hmacSignature2 = mac.doFinal("Hi There".getBytes());

      System.out.println("Check: " + Arrays.equals(hmacSignature, hmacSignature2));

   }
}
