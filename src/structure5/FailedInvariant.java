package structure5;

import structure5.FailedAssertion;

class FailedInvariant extends FailedAssertion {

   static final long serialVersionUID = 0L;


   public FailedInvariant(String reason) {
      super("\nInvariant that failed: " + reason);
   }
}
