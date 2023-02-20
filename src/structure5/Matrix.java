package structure5;

import structure5.Assert;
import structure5.Vector;

public class Matrix {

   protected int height;
   protected int width;
   protected Vector rows;


   public Matrix() {
      this(0, 0);
   }

   public Matrix(int h, int w) {
      this.height = h;
      this.width = w;
      this.rows = new Vector(this.height);

      for(int r = 0; r < this.height; ++r) {
         Vector theRow = new Vector(this.width);
         this.rows.add(theRow);

         for(int c = 0; c < this.width; ++c) {
            theRow.add((Object)null);
         }
      }

   }

   public Object get(int row, int col) {
      Assert.pre(0 <= row && row < this.height, "Row in bounds.");
      Assert.pre(0 <= col && col < this.width, "Col in bounds.");
      return ((Vector)this.rows.get(row)).get(col);
   }

   public void set(int row, int col, Object value) {
      Assert.pre(0 <= row && row < this.height, "Row in bounds.");
      Assert.pre(0 <= col && col < this.width, "Col in bounds.");
      ((Vector)this.rows.get(row)).set(col, value);
   }

   public void addRow(int r) {
      Assert.pre(0 <= r && r < this.width, "Row in bounds.");
      ++this.height;
      Vector theRow = new Vector(this.width);

      for(int c = 0; c < this.width; ++c) {
         theRow.add((Object)null);
      }

      this.rows.add(r, theRow);
   }

   public void addCol(int c) {
      Assert.pre(0 <= c && c < this.width, "Col in bounds.");
      ++this.width;

      for(int r = 0; r < this.height; ++r) {
         ((Vector)this.rows.get(r)).add(c, (Object)null);
      }

   }

   public Vector removeRow(int r) {
      Assert.pre(0 <= r && r < this.height, "There is a row to be removed.");
      Vector result = (Vector)this.rows.get(r);
      --this.height;
      this.rows.remove(r);
      return result;
   }

   public Vector removeCol(int c) {
      Assert.pre(0 <= c && c < this.width, "There is a column to be removed.");
      Vector result = new Vector(this.height);
      --this.width;

      for(int r = 0; r < this.height; ++r) {
         Vector theRow = (Vector)this.rows.get(r);
         result.add(theRow.get(c));
         theRow.remove(c);
      }

      return result;
   }

   public int width() {
      return this.width;
   }

   public int height() {
      return this.height;
   }

   public String toString() {
      StringBuffer s = new StringBuffer();
      s.append("<Matrix:\n");

      for(int r = 0; r < this.height; ++r) {
         for(int c = 0; c < this.width; ++c) {
            s.append("  <Row " + r + ", Col " + c + ", value=");
            s.append(this.get(r, c) + ">");
         }

         s.append("\n");
      }

      s.append(">");
      return s.toString();
   }
}
