package md;
import org.apache.commons.codec.digest.DigestUtils;

public class M4messageDigestCommonsCodec {

  public static void main(String[] args) {

    String password = "12345";
    String md5 = DigestUtils.md5Hex(password);

    System.out.println("password " + password);
    System.out.println("md5 digest is " + md5);

    String sha256 = DigestUtils.sha256Hex(password);

    System.out.println("password " + password);
    System.out.println("sha256 digest is " + sha256);
  }
}
