package Test;

import Model.Stack.StackList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackListTest {

    StackList<Integer> list = new StackList<>("single");
    StackListTest(){
        list.push(1);
        list.push(2);
        list.push(3);
    }
    @Test
    void count() {
        assertEquals(3, list.count());
    }

    @Test
    void isEmpty() {
        assertFalse(list.isEmpty());
    }

    @Test
    void peek() {
        assertEquals(3, list.peek());
    }
}
