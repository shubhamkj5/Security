package md;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;

public class M5mdCommonsCodecFile {

  public static void main(String[] args) throws Exception {
    File f = new File("data/test.txt");
    InputStream is = new FileInputStream(f);
    String sha256 = DigestUtils.sha256Hex(is);
    System.out.println("SHA-256: " + sha256);
  }
}
