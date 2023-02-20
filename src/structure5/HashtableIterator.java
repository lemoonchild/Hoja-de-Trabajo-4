package structure5;

import structure5.AbstractIterator;
import structure5.Association;
import structure5.HashAssociation;
import structure5.Vector;

class HashtableIterator extends AbstractIterator {

   protected int current;
   protected Vector data;


   public HashtableIterator(Vector table) {
      this.data = table;
      this.reset();
   }

   public void reset() {
      for(this.current = 0; this.current < this.data.size() && (this.data.get(this.current) == null || ((HashAssociation)this.data.get(this.current)).reserved()); ++this.current) {
         ;
      }

   }

   public boolean hasNext() {
      return this.current < this.data.size();
   }

   public HashAssociation next() {
      HashAssociation result = (HashAssociation)this.data.get(this.current);
      ++this.current;

      while(this.current < this.data.size() && (this.data.get(this.current) == null || ((HashAssociation)this.data.get(this.current)).reserved())) {
         ++this.current;
      }

      return result;
   }

   public Association get() {
      return (Association)this.data.get(this.current);
   }
}
