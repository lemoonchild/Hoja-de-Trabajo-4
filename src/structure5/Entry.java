package structure5;

import structure5.Assert;

public class Entry implements java.util.Map.Entry {

   protected Object theKey;
   protected Object theValue;


   public Entry(Object key, Object value) {
      Assert.pre(key != null, "Key must not be null.");
      this.theKey = key;
      this.theValue = value;
   }

   public Entry(Object key) {
      this(key, (Object)null);
   }

   public boolean equals(Object other) {
      java.util.Map.Entry otherEntry = (java.util.Map.Entry)other;
      return this.getKey().equals(otherEntry.getKey());
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
      Object result = this.theValue;
      this.theValue = value;
      return result;
   }

   public String toString() {
      StringBuffer s = new StringBuffer();
      s.append("<Entry: " + this.getKey() + "=" + this.getValue() + ">");
      return s.toString();
   }
}
