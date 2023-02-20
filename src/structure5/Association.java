package structure5;

import structure5.Assert;

public class Association implements java.util.Map.Entry {

   protected Object theKey;
   protected Object theValue;


   public Association(Object key, Object value) {
      Assert.pre(key != null, "Key must not be null.");
      this.theKey = key;
      this.theValue = value;
   }

   public Association(Object key) {
      this(key, (Object)null);
   }

   public boolean equals(Object other) {
      Association otherAssoc = (Association)other;
      return this.getKey().equals(otherAssoc.getKey());
   }

   public int hashCode() {
      return this.getKey().hashCode();
   }

   public Object getValue() {
      return this.theValue;
   }

   public Object getKey() {
      return this.theKey;
   }

   public Object setValue(Object value) {
      Object oldValue = this.theValue;
      this.theValue = value;
      return oldValue;
   }

   public String toString() {
      StringBuffer s = new StringBuffer();
      s.append("<Association: " + this.getKey() + "=" + this.getValue() + ">");
      return s.toString();
   }
}
