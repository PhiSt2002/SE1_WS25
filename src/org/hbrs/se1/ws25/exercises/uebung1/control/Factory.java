package org.hbrs.se1.ws25.exercises.uebung1.control;

/**
 * @author pstrun2s
 */

public class Factory {

    public static Translator createGermanTranslator() {
        GermanTranslator translator = new GermanTranslator();
        translator.setDate("11-11-2020");
        return translator;
    }
}
