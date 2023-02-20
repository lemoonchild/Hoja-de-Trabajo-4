package structure5;


public class Clock {

   protected boolean running = false;
   protected long strt = 0L;
   protected long accum = 0L;


   public void start() {
      this.running = true;
      this.strt = System.nanoTime();
   }

   public void stop() {
      this.running = false;
      this.accum += System.nanoTime() - this.strt;
   }

   public double read() {
      return (double)this.accum / 1.0E9D;
   }

   public void reset() {
      this.running = false;
      this.accum = 0L;
   }

   public String toString() {
      return "<Clock: " + this.read() + " seconds>";
   }
}
