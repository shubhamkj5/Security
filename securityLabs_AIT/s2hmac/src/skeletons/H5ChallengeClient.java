package skeletons;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class H5ChallengeClient {

   static Object readFromFile(String filename) throws Exception {
      FileInputStream fin = new FileInputStream(filename);
      ObjectInputStream oin = new ObjectInputStream(fin);
      Object object = oin.readObject();
      oin.close();
      return object;
   }

   public static void main(String[] args) {

      try {
         // read secret key from the file

         // Make a connection to the server
         InetAddress inet = InetAddress.getByName("localhost");
         Socket s = new Socket(inet, 2000);
         OutputStream o = s.getOutputStream();
         PrintWriter p = new PrintWriter(o);
         InputStream in = s.getInputStream();
         Scanner r = new Scanner(in);

         // Send a name
         p.println("paul");
         p.flush();

         // read challenge from server
    
         // HMAC it using the secret key.

         // Base64 encode the hmac and send to the server

         // read reply and print it out
      } catch (Exception e) {
         e.printStackTrace();
      } 
   }
}
