package structure5;


class FailedAssertion extends Error {

   static final long serialVersionUID = 0L;


   public FailedAssertion(String reason) {
      super("\nAssertion that failed: " + reason);
   }
}
