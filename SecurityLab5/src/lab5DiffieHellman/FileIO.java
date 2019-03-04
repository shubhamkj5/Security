package lab5DiffieHellman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileIO {
	static void writeToFile(String filename, Object object) throws Exception {
	    FileOutputStream fout = new FileOutputStream(new File(filename));
	    ObjectOutputStream oout = new ObjectOutputStream(fout);
	    oout.writeObject(object);
	    oout.close();
	}
	static Object readFromFile(String filename) throws Exception {
		FileInputStream fin = new FileInputStream(filename);
		ObjectInputStream oin = new ObjectInputStream(fin);
		Object object = oin.readObject();
		oin.close();
		return object;
		}
}
