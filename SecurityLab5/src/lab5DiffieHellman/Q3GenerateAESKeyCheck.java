package lab5DiffieHellman;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;

public class Q3GenerateAESKeyCheck {

	public static void main(String[] args) throws Exception {
		FileInputStream f1 = new FileInputStream("data/AlicePublic");
		 byte[] publicKey1bytes1024 = new byte[1024];
		 int size = f1.read(publicKey1bytes1024);
		 System.out.println(size);
		 byte[] publicKey1bytes = Arrays.copyOf(publicKey1bytes1024, size) ;

		 FileInputStream f2 = new FileInputStream("data/BobPublic");
		 byte[] publicKey2bytes1024 = new byte[425];
		 size = f2.read(publicKey2bytes1024);
		 System.out.println(size);
		 byte[] publicKey2bytes = Arrays.copyOf(publicKey2bytes1024, size) ;

		 PrivateKey privateKey1 = (PrivateKey) FileIO.readFromFile("data/AlicePrivate");
		 PrivateKey privateKey2 = (PrivateKey) FileIO.readFromFile("data/BobPrivate");
		 
		// get public keys
		 KeyFactory keyFact = KeyFactory.getInstance("DH");
		 
		 X509EncodedKeySpec x509KeySpec1 = new X509EncodedKeySpec(publicKey1bytes);
		 PublicKey publicKey1 = keyFact.generatePublic(x509KeySpec1);
		
		 X509EncodedKeySpec x509KeySpec2 = new X509EncodedKeySpec(publicKey2bytes);
		 PublicKey publicKey2 = keyFact.generatePublic(x509KeySpec2);

		 KeyAgreement ka = KeyAgreement.getInstance("DH");
		 String algorithm = "AES";

		 ka.init(privateKey1);
		 ka.doPhase(publicKey2, true);
		 SecretKey secretKey1 = ka.generateSecret(algorithm);
		 System.out.println(Base64.getEncoder().encodeToString(secretKey1.getEncoded()));
		
		 ka.init(privateKey2);
		 ka.doPhase(publicKey1, true);
		 SecretKey secretKey2 = ka.generateSecret(algorithm);
		 System.out.println(Base64.getEncoder().encodeToString(secretKey2.getEncoded()));
		 
	}

}
