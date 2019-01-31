import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class Q2File {
   public static void main(String args[]) throws Exception{
	   int count;
	   byte[] buffer= new byte[8192];
  
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      BufferedInputStream bis = new BufferedInputStream(new FileInputStream("C:\\Users\\A00258743\\Security\\test.txt"));
      while ((count = bis.read(buffer)) > 0) {
          md.update(buffer, 0, count);
      }
      bis.close();


      
     
      byte[] digest = md.digest();      
      System.out.println(digest);  
     
      
      StringBuffer hexString = new StringBuffer();
      
      for (int i = 0;i<digest.length;i++) {
         hexString.append(Integer.toHexString(0xFF & digest[i]));
      }
      System.out.println("Hex format : " + hexString.toString());     
   }
}
