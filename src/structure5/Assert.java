package structure5;

import structure5.FailedAssertion;
import structure5.FailedInvariant;
import structure5.FailedPostcondition;
import structure5.FailedPrecondition;

public class Assert {

   protected static int debugLevel = 0;


   private Assert() {
      fail("Attempt to construct an Assert!?");
   }

   public static void debugging() {
      ++debugLevel;
   }

   public static int debugLevel(int level) {
      int oldLevel = debugLevel;
      debugLevel = level;
      return oldLevel;
   }

   public static void debug(String message) {
      debug(1, message);
   }

   public static void debug(int level, String message) {
      if(level <= debugLevel) {
         System.err.println(message);
      }

   }

   public static void pre(boolean test, String message) {
      if(!test) {
         throw new FailedPrecondition(message);
      }
   }

   public static void post(boolean test, String message) {
      if(!test) {
         throw new FailedPostcondition(message);
      }
   }

   public static void condition(boolean test, String message) {
      if(!test) {
         throw new FailedAssertion(message);
      }
   }

   public static void invariant(boolean test, String message) {
      if(!test) {
         throw new FailedInvariant(message);
      }
   }

   public static void fail(String message) {
      throw new FailedAssertion(message);
   }

}
