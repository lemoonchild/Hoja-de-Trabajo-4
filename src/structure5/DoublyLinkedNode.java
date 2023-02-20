package structure5;


public class DoublyLinkedNode {

   protected Object data;
   protected DoublyLinkedNode nextElement;
   protected DoublyLinkedNode previousElement;


   public DoublyLinkedNode(Object v, DoublyLinkedNode next, DoublyLinkedNode previous) {
      this.data = v;
      this.nextElement = next;
      if(this.nextElement != null) {
         this.nextElement.previousElement = this;
      }

      this.previousElement = previous;
      if(this.previousElement != null) {
         this.previousElement.nextElement = this;
      }

   }

   public DoublyLinkedNode(Object v) {
      this(v, (DoublyLinkedNode)null, (DoublyLinkedNode)null);
   }

   public DoublyLinkedNode next() {
      return this.nextElement;
   }

   public DoublyLinkedNode previous() {
      return this.previousElement;
   }

   public Object value() {
      return this.data;
   }

   public void setNext(DoublyLinkedNode next) {
      this.nextElement = next;
   }

   public void setPrevious(DoublyLinkedNode previous) {
      this.previousElement = previous;
   }

   public void setValue(Object value) {
      this.data = value;
   }

   public boolean equals(Object other) {
      DoublyLinkedNode that = (DoublyLinkedNode)other;
      return that == null?false:(that.value() != null && this.value() != null?this.value().equals(that.value()):this.value() == that.value());
   }

   public int hashCode() {
      return this.value() == null?super.hashCode():this.value().hashCode();
   }

   public String toString() {
      return "<DoublyLinkedNode: " + this.value() + ">";
   }
}
