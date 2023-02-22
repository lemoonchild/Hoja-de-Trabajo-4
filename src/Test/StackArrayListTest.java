package Test;

import Model.Stack.StackArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackArrayListTest {
    StackArrayList array = new StackArrayList();

    StackArrayListTest(){
        array.push(1);
        array.push(2);
        array.push(3);
    }
    @Test
    void count() {
       assertEquals(3, array.count());
    }

    @Test
    void isEmpty() {
        assertFalse(array.isEmpty());
    }

    @Test
    void peek() {
        assertEquals(3, array.peek());
    }
}