import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class H5ChallengeClient {

   static Object readFromFile(String filename) throws Exception {
      FileInputStream fin = new FileInputStream(filename);
      ObjectInputStream oin = new ObjectInputStream(fin);
      Object object = oin.readObject();
      oin.close();
      return object;
   }

   public static void main(String[] args) throws Exception {

      // read secret key from the file
      SecretKey sk = (SecretKey) readFromFile("data/secretKey");

      // Make a connection to the server
      InetAddress inet = InetAddress.getByName("localhost");
      Socket s = new Socket(inet, 2000);

      OutputStream o = s.getOutputStream();
      PrintWriter p = new PrintWriter(o);
      InputStream in = s.getInputStream();
      Scanner r = new Scanner(in);

      // Send a name
      p.println("jain");
      p.flush();

      // read challenge from server
      String challenge = r.nextLine();
      System.out.println("Client: Challenge is: " + challenge);

      // HMAC it using the secret key.
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(sk);
      byte[] hmac = mac.doFinal(challenge.getBytes());

      // Base64 encode the hmac and send to the server
      String hmacEncoded = Base64.getEncoder().encodeToString(hmac);
      p.println(hmacEncoded);
      p.flush();

      // have we passed
      String reply = r.nextLine();
      System.out.println(reply);
   }
}
