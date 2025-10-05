package org.hbrs.se1.ws25.tests.uebung1;

import org.hbrs.se1.ws25.exercises.uebung1.control.GermanTranslator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GermanTranslatorTest {

    @Test
    void testValidNumbers() {
        GermanTranslator translator = new GermanTranslator();
        assertEquals("eins", translator.translateNumber(1));
        assertEquals("fünf", translator.translateNumber(5));
        assertEquals("zehn", translator.translateNumber(10));
    }

    @Test
    void testInvalidNumbersTooLow() {
        GermanTranslator translator = new GermanTranslator();
        assertTrue(translator.translateNumber(-1).contains("nicht möglich"));
        assertTrue(translator.translateNumber(-18).contains("nicht möglich"));
    }

    @Test
    void testInvalidNumbersTooHigh() {
        GermanTranslator translator = new GermanTranslator();
        assertTrue(translator.translateNumber(11).contains("nicht möglich"));
        assertTrue(translator.translateNumber(42).contains("nicht möglich"));
    }

}