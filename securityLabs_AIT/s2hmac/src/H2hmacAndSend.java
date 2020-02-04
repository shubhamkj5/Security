import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class H2hmacAndSend {
   static void writeToFile(String filename, Object object) throws Exception {
      FileOutputStream fout = new FileOutputStream(filename);
      ObjectOutputStream oout = new ObjectOutputStream(fout);
      oout.writeObject(object);
      oout.close();
   }

   public static void main(String[] args) throws Exception {
      String message = "This is the data I am sending";
      // write secret key
      KeyGenerator kg = KeyGenerator.getInstance("HmacSHA256");
      SecretKey sk = kg.generateKey();
      String encodedKey = Base64.getEncoder().encodeToString(sk.getEncoded());
      writeToFile("data/secretKey", encodedKey);

      // write message
      writeToFile("data/sendText.txt", message);

      // write hmac
      byte[] textArray = message.getBytes();
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(sk);
      byte[] hmac = mac.doFinal(textArray);
      String encodedHmac = Base64.getEncoder().encodeToString(hmac);
      writeToFile("data/hmac", encodedHmac);

      System.out.println(hmac.length);
      System.out.println("Base64 encoded message digest " + encodedHmac);
   }
}
