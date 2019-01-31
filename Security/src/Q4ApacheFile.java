import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;

public class Q4ApacheFile {
public static void main(String[] args) {
	 String data = "C:\\Users\\A00258743\\Security\\test.txt";
     File file = new File(data);
     try {
         InputStream is = new FileInputStream(file);
         String digest = DigestUtils.sha256Hex(is);
         System.out.println("Digest          = " + digest);
         System.out.println("Digest.length() = " + digest.length());
     } catch (IOException e) {
         e.printStackTrace();
     }
}
}
