package lab5DiffieHellman;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Q4ServerSkel {

   public static void main(String[] args) throws Exception {

      Socket s;
      ServerSocket ss = new ServerSocket(2000);
      while (true) {
         System.out.println("Server: waiting for connection ..");

         // Socket
         s = ss.accept();
         InputStream in = s.getInputStream();
         Scanner r = new Scanner(in);
         OutputStream o = s.getOutputStream();
         PrintWriter p = new PrintWriter(o);

         // Read DH params as string
         String params = r.nextLine();
         
         // create DHParameterSpec object
         String[] values = params.split(",");
         BigInteger pp = new BigInteger(values[0]);
         BigInteger g = new BigInteger(values[1]);
         int l = Integer.parseInt(values[2]);
         DHParameterSpec dhSpec = new DHParameterSpec(pp, g, l);
         
         
         // generate own DH key pair (using DHParameterSpec object)
         KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DH");
         keyGen.initialize(dhSpec);
         KeyPair keypair = keyGen.generateKeyPair();
         PrivateKey privateKey = keypair.getPrivate();
         PublicKey publicKey = keypair.getPublic();
         
         // read Base64encoded client public key
         String input = r.nextLine();
         byte[] serverPublicKeyBytes = Base64.getDecoder().decode(input);
         
         // convert it to a public key object
         KeyFactory keyFactory = KeyFactory.getInstance("DH");
         X509EncodedKeySpec x509KeySpec1 = new X509EncodedKeySpec(serverPublicKeyBytes);
         PublicKey clientPublicKey = keyFactory.generatePublic(x509KeySpec1);
 

         // send own Base64 encoded public key
         String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
         p.println(publicKeyBase64);
         p.flush();
         System.out.println("Server public key sent");
        
         // generate symmetric key
         KeyAgreement ka = KeyAgreement.getInstance("DH");
         ka.init(privateKey);
         ka.doPhase(clientPublicKey, true);
         // SecretKey secretKey1 = ka.generateSecret(algorithm);
         byte[] rawValue = ka.generateSecret();
         SecretKey secretKey = new SecretKeySpec(rawValue, 0, 16, "AES");

         // Base64 encode the Secret key and print it out
         String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
         System.out.println("Base64 encoded secret key " + encodedKey);

         
         p.close();
      }
   }

}
