package structure5;

import structure5.Linear;

public interface Queue extends Linear {

   void add(Object var1);

   void enqueue(Object var1);

   Object remove();

   Object dequeue();

   Object getFirst();

   Object get();

   Object peek();

   boolean empty();

   int size();
}
