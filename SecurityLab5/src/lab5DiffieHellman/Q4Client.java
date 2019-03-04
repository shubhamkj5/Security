package lab5DiffieHellman;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Q4Client {

   public static void main(String[] args) throws Exception {

      System.out.println("Client");

      // Socket
      InetAddress inet = InetAddress.getByName("localhost");
      Socket s = new Socket(inet, 2000);

      OutputStream o = s.getOutputStream();
      PrintWriter p = new PrintWriter(o);
      InputStream in = s.getInputStream();
      Scanner r = new Scanner(in);

      // get DH params as string
      String params = generateParams();
      
      // send DH params as string
      p.println(params);
      p.flush();
      
      // create DHParameterSpec object
      String[] values = params.split(",");
      BigInteger pp = new BigInteger(values[0]);
      BigInteger g = new BigInteger(values[1]);
      int l = Integer.parseInt(values[2]);
      DHParameterSpec dhSpec = new DHParameterSpec(pp, g, l);
      
      // Generate a DH key pair  (using DHParameterSpec object)
      KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DH");
      keyGen.initialize(dhSpec);
      KeyPair keypair = keyGen.generateKeyPair();
      PrivateKey privateKey = keypair.getPrivate();
      PublicKey publicKey = keypair.getPublic();

      // send own Base64 encoded public key
      String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
      p.println(publicKeyBase64);
      p.flush();
      System.out.println("client public key sent");

      // read Base64 encoded servers public key
      String input = r.nextLine();
      byte[] serverPublicKeyBytes = Base64.getDecoder().decode(input);
      // convert it to a public key object
      KeyFactory keyFactory = KeyFactory.getInstance("DH");
      X509EncodedKeySpec x509KeySpec1 = new X509EncodedKeySpec(serverPublicKeyBytes);
      PublicKey serverPublicKey = keyFactory.generatePublic(x509KeySpec1);

      // generate symmetric key
      KeyAgreement ka = KeyAgreement.getInstance("DH");
      ka.init(privateKey);
      ka.doPhase(serverPublicKey, true);
      // SecretKey secretKey1 = ka.generateSecret(algorithm);
      byte[] rawValue = ka.generateSecret();
      SecretKey secretKey = new SecretKeySpec(rawValue, 0, 16, "AES");

      // Base64 encode the Secret key and print it out
      String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
      System.out.println("Base64 encoded secret key " + encodedKey);
   }

   public static String generateParams() {

      String s = null;
      try {
         // Create the parameter generator for a 1024-bit DH key pair
         AlgorithmParameterGenerator paramGen = AlgorithmParameterGenerator.getInstance("DH");
         paramGen.init(1024);

         // Generate the parameters
         AlgorithmParameters params = paramGen.generateParameters();
         DHParameterSpec dhSpec = (DHParameterSpec) params.getParameterSpec(DHParameterSpec.class);
         s = dhSpec.getP() + "," + dhSpec.getG() + "," + dhSpec.getL();

      } catch (Exception e) {
         e.printStackTrace();
      }
      return s;
   }
}
