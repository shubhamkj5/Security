package lab5DiffieHellman;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.spec.DHParameterSpec;

public class Q1GenParam {
	
		
		
        public static void main(String[] args) throws Exception {
        			AlgorithmParameterGenerator paramGen = AlgorithmParameterGenerator.getInstance("DH");
        			 paramGen.init(1024);
        			 
        			 // Generate the parameters
        			 AlgorithmParameters params = paramGen.generateParameters();
        			 DHParameterSpec dhSpec = params.getParameterSpec(DHParameterSpec.class);
        			 
        			/* DHParameterSpec(BigInteger p, BigInteger g, int l)
        			 Constructs a parameter set for Diffie-Hellman, using a 
        			 prime modulus p, 
        			 a base generator g, and 
        			 the size in bits, l, of the random exponent (private value).*/
        			 String s = dhSpec.getP() + "," + dhSpec.getG() + "," + dhSpec.getL();
        			 FileIO.writeToFile("data/dhParams", s) ;
        			 
        			 System.out.println("P: "+dhSpec.getP());
        			 System.out.println("G: "+dhSpec.getG());
        			 System.out.println("L: "+dhSpec.getL());
        			
		}
}
