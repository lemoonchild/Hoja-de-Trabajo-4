package structure5;


public class Node {

   protected Object data;
   protected Node nextElement;


   public Node(Object v, Node next) {
      this.data = v;
      this.nextElement = next;
   }

   public Node(Object v) {
      this(v, (Node)null);
   }

   public Node next() {
      return this.nextElement;
   }

   public void setNext(Node next) {
      this.nextElement = next;
   }

   public Object value() {
      return this.data;
   }

   public void setValue(Object value) {
      this.data = value;
   }

   public String toString() {
      return "<Node: " + this.value() + ">";
   }
}
