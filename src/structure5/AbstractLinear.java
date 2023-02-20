package structure5;

import structure5.AbstractStructure;
import structure5.Assert;
import structure5.Linear;

public abstract class AbstractLinear extends AbstractStructure implements Linear {

   public boolean empty() {
      return this.isEmpty();
   }

   public Object remove(Object o) {
      Assert.fail("Method not implemented.");
      return null;
   }
}
