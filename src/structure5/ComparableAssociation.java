package structure5;

import structure5.Association;

public class ComparableAssociation extends Association implements Comparable, java.util.Map.Entry {

   public ComparableAssociation(Comparable key) {
      this(key, (Object)null);
   }

   public ComparableAssociation(Comparable key, Object value) {
      super(key, value);
   }

   public int compareTo(ComparableAssociation that) {
      return ((Comparable)this.getKey()).compareTo(that.getKey());
   }

   public String toString() {
      StringBuffer s = new StringBuffer();
      s.append("<ComparableAssociation: " + this.getKey() + "=" + this.getValue() + ">");
      return s.toString();
   }
}
