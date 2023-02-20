package structure5;

import structure5.BitSet;

public class CharSet {

   protected BitSet s = new BitSet();


   public void add(char c) {
      this.s.add(c);
   }

   public void remove(char c) {
      this.s.remove(c);
   }

   public boolean contains(char c) {
      return this.s.contains(c);
   }

   public void clear() {
      this.s.clear();
   }

   public Object clone() {
      CharSet duplicate = new CharSet();
      duplicate.s = (BitSet)this.s.clone();
      return duplicate;
   }

   public Object union(CharSet other) {
      return this.s.union(other.s);
   }

   public Object intersection(CharSet other) {
      return this.s.intersection(other.s);
   }

   public Object difference(CharSet other) {
      return this.s.difference(other.s);
   }

   public boolean subset(CharSet other) {
      return this.s.subset(other.s);
   }

   public boolean isEmpty() {
      return this.s.isEmpty();
   }

   public boolean equals(Object other) {
      return this.s.equals(((CharSet)other).s);
   }

   public String toString() {
      StringBuffer sb = new StringBuffer();
      boolean i = false;
      sb.append("<CharSet:");

      for(int var3 = 0; this.s.probe(var3); ++var3) {
         if(this.s.contains(var3)) {
            if(var3 >= 32 && var3 <= 126) {
               sb.append(" (char)" + var3);
            } else {
               sb.append(" " + (char)var3);
            }
         }
      }

      sb.append(">");
      return this.s.toString();
   }
}
