package org.hbrs.se1.ws25.exercises.uebung1.control;

public class GermanTranslator implements Translator {

	public String date = "Okt/2025"; // Default-Wert

    private String[] zahlen = { "eins", "zwei", "drei", "vier" , "fünf", "sechs" , "sieben" , "acht" , "neun" , "zehn" };

	/**
	 * Methode zur Übersetzung einer Zahl in eine String-Repraesentation
	 */
	 public String translateNumber(int number) {
         String result = "";
         try {
             result = zahlen[number-1];
         } catch (ArrayIndexOutOfBoundsException e) {
            result = "Übersetzung der Zahl " + number + " nicht möglich! (V " + version + ")";
         } finally {
             return result;
         }
	}

	/**
	 * Objektmethode der Klasse GermanTranslator zur Ausgabe einer Info.
	 */
	void printInfo(){
		System.out.println( "GermanTranslator v1.9, erzeugt am " + this.date );
	}

	/**
	 * Setzen des Datums, wann der Uebersetzer erzeugt wurde (Format: Monat/Jahr (Beispiel: "Okt/2024"))
	 * Das Datum sollte system-intern durch eine Factory-Klasse gesetzt werden und nicht von externen View-Klassen
	 */
	public void setDate( String date ) {
		this.date = date;
	}

}
