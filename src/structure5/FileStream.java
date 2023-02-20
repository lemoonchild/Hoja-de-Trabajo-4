package structure5;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class FileStream extends InputStream {

   private FileInputStream in;


   public FileStream(String name) {
      try {
         this.in = new FileInputStream(name);
      } catch (Exception var3) {
         throw new RuntimeException("Cannot access file " + name);
      }
   }

   public int available() {
      try {
         return this.in.available();
      } catch (Exception var2) {
         throw new RuntimeException(var2.toString());
      }
   }

   public void close() {
      try {
         this.in.close();
      } catch (Exception var2) {
         throw new RuntimeException(var2.toString());
      }
   }

   public void mark(int readlimit) {
      try {
         this.in.mark(readlimit);
      } catch (Exception var3) {
         throw new RuntimeException(var3.toString());
      }
   }

   public boolean markSupported() {
      try {
         return this.in.markSupported();
      } catch (Exception var2) {
         throw new RuntimeException(var2.toString());
      }
   }

   public int read() {
      try {
         return this.in.read();
      } catch (Exception var2) {
         throw new RuntimeException(var2.toString());
      }
   }

   public void reset() {
      try {
         this.in.reset();
      } catch (Exception var2) {
         throw new RuntimeException(var2.toString());
      }
   }

   public long skip(long n) {
      try {
         return this.in.skip(n);
      } catch (Exception var4) {
         throw new RuntimeException(var4.toString());
      }
   }

   public static void main(String[] args) {
      FileStream in = new FileStream(args[0]);
      Scanner s = new Scanner(in);

      while(s.hasNextLine()) {
         System.out.println(s.nextLine());
      }

   }
}
