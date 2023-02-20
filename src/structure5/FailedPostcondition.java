package structure5;

import structure5.FailedAssertion;

class FailedPostcondition extends FailedAssertion {

   static final long serialVersionUID = 0L;


   public FailedPostcondition(String reason) {
      super("\nA postcondition: " + reason);
   }
}
