package structure5;

import structure5.Linear;

public interface Stack extends Linear {

   void add(Object var1);

   void push(Object var1);

   Object remove();

   Object pop();

   Object get();

   Object getFirst();

   Object peek();

   boolean empty();

   int size();
}
