package structure5;

import structure5.Assert;
import structure5.Association;

public class HashAssociation extends Association {

   protected boolean reserved;


   public HashAssociation(Object key, Object value) {
      super(key, value);
      this.reserved = false;
   }

   public HashAssociation(Object key) {
      this(key, (Object)null);
   }

   public Object getValue() {
      Assert.pre(!this.reserved, "Reserved HashAssociations may not be read.");
      return super.getValue();
   }

   public Object getKey() {
      Assert.pre(!this.reserved, "Reserved HashAssociations may not be read.");
      return super.getKey();
   }

   public Object setValue(Object value) {
      Assert.pre(!this.reserved, "Reserved HashAssociations may not be written.");
      return super.setValue(value);
   }

   public boolean reserved() {
      return this.reserved;
   }

   public void reserve() {
      Assert.pre(!this.reserved, "HashAssociation reserved twice.");
      this.reserved = true;
   }

   public String toString() {
      StringBuffer s = new StringBuffer();
      if(this.reserved()) {
         s.append("<ReservedAssociation: RESERVED>");
      } else {
         s.append("<ReservedAssociation: " + this.getKey() + "=" + this.getValue() + ">");
      }

      return s.toString();
   }
}
