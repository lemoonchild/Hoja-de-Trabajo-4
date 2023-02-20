package structure5;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import structure5.Assert;

public class ReadStream extends FilterInputStream {

   protected DataInputStream strm;
   protected boolean atEOF;
   protected char[] buffer;
   protected int buffersize;
   protected int buffertop;
   protected boolean absorbNL;


   public ReadStream() {
      this(System.in);
   }

   public ReadStream(InputStream strm) {
      super(new DataInputStream(strm));
      this.absorbNL = false;
      this.strm = (DataInputStream)this.in;
      this.atEOF = false;
      this.buffer = new char[8];
      this.buffersize = 8;
      this.buffertop = -1;
   }

   public boolean eof() {
      if(this.atEOF) {
         return true;
      } else {
         this.getFirst();
         return this.atEOF;
      }
   }

   private static boolean isWhite(char c) {
      return Character.isWhitespace(c);
   }

   public char getFirst() {
      char c = this.readChar();
      this.pushbackChar(c);
      return c;
   }

   public boolean eoln() {
      char c = this.getFirst();
      return this.eof() || c == 10 || c == 13;
   }

   public void readln() {
      this.readLine();
   }

   public void skipWhite() {
      char c;
      for(c = this.readChar(); isWhite(c); c = this.readChar()) {
         ;
      }

      this.pushbackChar(c);
   }

   public String readString() {
      char[] buffer = new char[512];
      boolean c = false;
      int count = 0;
      this.skipWhite();

      while(!this.eof()) {
         char var4 = this.readChar();
         if(isWhite(var4)) {
            this.pushbackChar(var4);
            break;
         }

         buffer[count++] = var4;
      }

      return new String(buffer, 0, count);
   }

   private boolean acceptChar(char c) {
      char d = this.readChar();
      if(Character.toLowerCase(c) == Character.toLowerCase(d)) {
         return true;
      } else {
         this.pushbackChar(d);
         return false;
      }
   }

   private boolean acceptWord(String s) {
      this.skipWhite();

      for(int i = 0; i < s.length(); ++i) {
         if(!this.acceptChar(s.charAt(i))) {
            for(int j = i - 1; j >= 0; --j) {
               this.pushbackChar(s.charAt(j));
            }

            return false;
         }
      }

      return true;
   }

   public boolean readBoolean() {
      if(this.acceptWord("true")) {
         return true;
      } else {
         if(!this.acceptWord("false")) {
            Assert.fail("Boolean not found on input.");
         }

         return false;
      }
   }

   public char readChar() {
      char c = 0;

      try {
         if(this.atEOF) {
            char e = 0;
            return e;
         }

         if(this.buffertop >= 0) {
            c = this.buffer[this.buffertop--];
         } else {
            c = (char)this.strm.readByte();
         }
      } catch (EOFException var7) {
         this.atEOF = true;
      } catch (IOException var8) {
         Assert.fail("Input error free.");
      } finally {
         if(this.absorbNL && c == 10) {
            this.absorbNL = false;
            c = this.readChar();
         }

         this.absorbNL = c == 13;
      }

      return c;
   }

   public void pushbackChar(char c) {
      if(c != 0) {
         this.atEOF = false;
         ++this.buffertop;
         if(this.buffertop == this.buffersize) {
            char[] old = this.buffer;
            this.buffersize *= 2;
            this.buffer = new char[this.buffersize];

            for(int i = 0; i < this.buffertop; ++i) {
               this.buffer[i] = old[i];
            }
         }

         this.buffer[this.buffertop] = c;
         this.absorbNL = false;
      }
   }

   public double readDouble() {
      StringBuffer sb = new StringBuffer();
      this.skipWhite();
      if(this.acceptChar('+')) {
         sb.append('+');
      } else if(this.acceptChar('-')) {
         sb.append('-');
      }

      char c;
      for(c = this.readChar(); Character.isDigit(c); c = this.readChar()) {
         sb.append(c);
      }

      this.pushbackChar(c);
      if(this.acceptChar('.')) {
         sb.append('.');

         for(c = this.readChar(); Character.isDigit(c); c = this.readChar()) {
            sb.append(c);
         }

         this.pushbackChar(c);
      }

      if(this.acceptChar('E')) {
         sb.append('E');
         if(this.acceptChar('+')) {
            sb.append('+');
         } else if(this.acceptChar('-')) {
            sb.append('-');
         }

         for(c = this.readChar(); Character.isDigit(c); c = this.readChar()) {
            sb.append(c);
         }

         this.pushbackChar(c);
      }

      String s = sb.toString();
      return Double.valueOf(s).doubleValue();
   }

   public float readFloat() {
      return (float)this.readDouble();
   }

   public void readFully(byte[] b) throws IOException {
      this.strm.readFully(b);
   }

   public void readFully(byte[] b, int off, int len) throws IOException {
      this.strm.readFully(b, off, len);
   }

   public short readShort() {
      return (short)((int)this.readLong());
   }

   public int readInt() {
      return (int)this.readLong();
   }

   public long readLong() {
      boolean negate = false;
      int digitsRead = 0;
      long value = 0L;
      byte base = 10;
      this.skipWhite();
      if(this.eof()) {
         return 0L;
      } else {
         while(!this.eof()) {
            char c = this.readChar();
            if(digitsRead == 0 && c == 45) {
               negate = true;
            } else if(digitsRead == 0 && c == 48) {
               base = 8;
               ++digitsRead;
            } else if(digitsRead == 1 && base == 8 && (c == 120 || c == 88)) {
               base = 16;
               ++digitsRead;
            } else {
               int d = c - 48;
               if(c >= 97 && c <= 102) {
                  d = c - 97 + 10;
               } else if(c >= 65 && c <= 70) {
                  d = c - 65 + 10;
               }

               if(d < 0 || d >= base) {
                  this.pushbackChar(c);
                  break;
               }

               ++digitsRead;
               value = value * (long)base + (long)d;
            }
         }

         if(negate) {
            value = -value;
         }

         return value;
      }
   }

   public String readLine() {
      StringBuffer result = new StringBuffer();

      while(!this.eoln()) {
         result.append(this.readChar());
      }

      this.readChar();
      return result.toString();
   }

   public String readUTF() throws IOException {
      return this.strm.readUTF();
   }
}
