package Test;

import Controller.infixToPostfix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class infixToPostfixTest {

    @Test
    void infixToPostfixTest() {
        assertEquals("435+7+", infixToPostfix.converterPostfix("4(3+5)+7"));
    }
}