package lab5DiffieHellman;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

import javax.crypto.spec.DHParameterSpec;

public class Q2SaveKeys {
	
		public static void main(String[] args) throws Exception {
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Party Name:");
			String PARTY = sc.next();
			
			 // get DH parameters
			 String valuesInStr = (String) FileIO.readFromFile("data/dhParams");
			 String[] values = valuesInStr.split(",");
			 BigInteger p = new BigInteger(values[0]);
			 BigInteger g = new BigInteger(values[1]);
			 int l = Integer.parseInt(values[2]);
			 DHParameterSpec dhSpec = new DHParameterSpec(p, g, l);
			
			 // Use the values to generate a key pair
			 KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DH");
			 keyGen.initialize(dhSpec);
			 KeyPair keypair = keyGen.generateKeyPair();
			 
			// Save the private key
			 PrivateKey privateKey = keypair.getPrivate();
			 FileIO.writeToFile("data/" + PARTY + "Private", privateKey) ;
			 
			 // Save the public key
			 PublicKey publicKey = keypair.getPublic();
			 byte[] publicKeyBytes = publicKey.getEncoded();
			 System.out.println(publicKeyBytes.length);
			 FileOutputStream fout2 = new FileOutputStream("data/" + PARTY + "Public");
			 fout2.write(publicKeyBytes);
			 fout2.close();
		}
}
