package zold;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class H1hmacExBase64Encode {

   public static void main(String[] args) throws Exception {

      String message = "hi there";
      // Generate secret key for HMAC-MD5
      KeyGenerator kg = KeyGenerator.getInstance("HmacSHA256");
      SecretKey sk = kg.generateKey();

      // Get instance of Mac object implementing HMAC-MD5, and
      // initialize it with the above secret key
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(sk);
      byte[] hmacSignature = mac.doFinal(message.getBytes());
      System.out.println(hmacSignature.length);

      // Base64 encode a secret key
      String encodedKey = Base64.getEncoder().encodeToString(sk.getEncoded());
      System.out.println("Encoded Key :" + encodedKey);

      /////////////////////////////////////////////////////
      /// Receiver

      // Base64 decode a secret key
      byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
      SecretKey sk2 = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");

      Mac mac2 = Mac.getInstance("HmacSHA256");
      mac2.init(sk2);
      byte[] hmacSignature2 = mac.doFinal(message.getBytes());

      System.out.println("Check: " + Arrays.equals(hmacSignature, hmacSignature2));

   }

}
