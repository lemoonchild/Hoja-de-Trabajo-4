package structure5;

import structure5.FailedAssertion;

class FailedPrecondition extends FailedAssertion {

   static final long serialVersionUID = 0L;


   public FailedPrecondition(String reason) {
      super("\nA precondition: " + reason);
   }
}
