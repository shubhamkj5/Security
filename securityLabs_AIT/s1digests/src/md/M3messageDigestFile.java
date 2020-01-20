package md;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Base64;

public class M3messageDigestFile {
	public static void main(String[] args) {
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			File f = new File("data/test.txt");
			InputStream is = new FileInputStream(f);
			byte[] buffer = new byte[8192];

			int read = 0;
			while ((read = is.read(buffer)) > 0) {
				algorithm.update(buffer, 0, read);
			}

			byte[] digest = algorithm.digest();

			String encodedDigest = Base64.getEncoder().encodeToString(digest);
			System.out.println("Base64 encoded message digest " + encodedDigest);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
