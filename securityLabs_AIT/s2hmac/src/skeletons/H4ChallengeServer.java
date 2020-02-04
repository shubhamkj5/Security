package skeletons;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class H4ChallengeServer {

   static void writeToFile(String filename, Object object) throws Exception {
      FileOutputStream fout = new FileOutputStream(filename);
      ObjectOutputStream oout = new ObjectOutputStream(fout);
      oout.writeObject(object);
      oout.close();
   }

   public static void main(String[] args) {
      try {
         String challenge = "We give up, come and rule us";

         // generate secret and write to file
    
         // calculate the correct response to challenge
    
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
    
         // read response from client

         // Compare the hmac received from the client with the hmac calculated
         // If they match, send a message "success" to the client
         // If they don't, send a message "fails" to the client


         // close the connection
         p.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
