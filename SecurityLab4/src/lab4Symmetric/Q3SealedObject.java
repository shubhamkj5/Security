package lab4Symmetric;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

public class Q3SealedObject {

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, IOException, ClassNotFoundException, BadPaddingException {
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
	     System.out.println("Sealed Object: "+sealedEm1);
	     
		/////////////////////////////////////////////////
		// Decrypt
		
		Cipher dCipher = Cipher.getInstance(ALGORITHM);
		dCipher.init(Cipher.DECRYPT_MODE, key);
		
		Employee em2 = (Employee) sealedEm1.getObject(dCipher);
		System.out.println(em2.name+" "+em2.address+" "+em2.tel);
	}

}

class Employee implements Serializable {
    public String name = "";
    public String address = "";
    public int tel = 0;

    public Employee(String name, String address,int tel) {
        this.name = name;
        this.address = address;
        this.tel=tel;
    }

}
