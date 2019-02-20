package lab4Symmetric;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

public class Q4SealedObWrite {
	
	private static void writeToFile(String filename, Object object) throws Exception {
		    FileOutputStream fout = new FileOutputStream(new File(filename));
		    ObjectOutputStream oout = new ObjectOutputStream(fout);
		    oout.writeObject(object);
		    oout.close();
		}

	
		 public static void main(String[] args) throws Exception {
			// create an employee object
	         Employee em1 = new Employee("Jain", "Willow",1234567890);
	        
	         String ALGORITHM = "AES" ;
		   	 KeyGenerator keygen = KeyGenerator.getInstance(ALGORITHM);
		   	 SecretKey key = keygen.generateKey();
		   	 Cipher eCipher = Cipher.getInstance(ALGORITHM);
		   	
		   	 // Initialise the cipher for encryption
		   	 eCipher.init(Cipher.ENCRYPT_MODE, key);
		   	 
		     // create sealed object
		     SealedObject sealedEm1 = new SealedObject( em1, eCipher);
		     writeToFile("data/key.txt", key);
		     writeToFile("data/sealedObject.dat", sealedEm1);
		     System.out.println("Sealed Object: "+sealedEm1);
		     System.out.println("Write Success");
		     
			
		}
}
