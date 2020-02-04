import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class H4ChallengeServer {

   static void writeToFile(String filename, Object object) throws Exception {
      FileOutputStream fout = new FileOutputStream(filename);
      ObjectOutputStream oout = new ObjectOutputStream(fout);
      oout.writeObject(object);
      oout.close();
   }

   public static void main(String[] args) throws Exception {
      String challenge = "We give up, come and rule us";

      // generate secret and write to file
      KeyGenerator kg = KeyGenerator.getInstance("HmacSHA256");
      SecretKey sk = kg.generateKey();
      writeToFile("data/secretKey", sk);

      // calculate the correct response to challenge
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(sk);
      byte[] correctResponse = mac.doFinal(challenge.getBytes());

      // Accept connection from client
      Socket s;
      ServerSocket ss = new ServerSocket(2000);

      System.out.println("Server: waiting for connection ..");
      s = ss.accept();

      InputStream in = s.getInputStream();
      Scanner r = new Scanner(in);
      OutputStream o = s.getOutputStream();
      PrintWriter p = new PrintWriter(o);

      // read name and print it out
      String inputLine;
      inputLine = r.nextLine();
      System.out.println("Hello " + inputLine);

      // send challenge
      p.println(challenge);
      p.flush();

      // read response from client
      String reply = r.nextLine();
      byte[] clientHmac = Base64.getDecoder().decode(reply);

      // Compare the value received from the client with the value calculated
      // If they match, send a message "success" to the client
      // If they don't, send a message "fails" to the client
      if (Arrays.equals(correctResponse, clientHmac)) {
         p.println("Passed ");
      } else {
         p.println("Failed ");
      }
      p.close();
   }
}
