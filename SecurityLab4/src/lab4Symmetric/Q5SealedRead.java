package lab4Symmetric;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

public class Q5SealedRead {
	
	
	static Object readFromFile(String filename) throws Exception {
		FileInputStream fin = new FileInputStream(filename);
		ObjectInputStream oin = new ObjectInputStream(fin);
		Object object = oin.readObject();
		oin.close();
		return object;
		}
	
	
	public static void main(String[] args) throws Exception {
			String ALGORITHM = "AES" ;
		   	
		    SecretKey key =  (SecretKey) readFromFile("data/key.txt");
		   	Cipher eCipher = Cipher.getInstance(ALGORITHM);
			
		   	/////////////////////////////////////////////////
			// Decrypt
		
			eCipher.init(Cipher.DECRYPT_MODE, key);
			
		   
	        SealedObject sealedObject = (SealedObject) readFromFile("data/sealedObject.dat");
	        Employee em2 = (Employee) sealedObject.getObject(eCipher);
			System.out.println(em2.name+" "+em2.address+" "+em2.tel);

	}

}
