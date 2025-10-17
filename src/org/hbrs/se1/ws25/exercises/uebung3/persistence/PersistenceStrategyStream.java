package org.hbrs.se1.ws25.exercises.uebung3.persistence;

import java.io.*;
import java.util.List;

import static org.hbrs.se1.ws25.exercises.uebung3.persistence.PersistenceException.ExceptionType.*;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "objects.ser";

    private ObjectOutputStream oos = null;
    private FileOutputStream fos = null;

    private FileInputStream fis = null;
    private ObjectInputStream ois = null;

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     * Look-up in Google for further help! Good source:
     * https://www.digitalocean.com/community/tutorials/objectoutputstream-java-write-object-file
     * (Last Access: Oct, 13th 2025)
     */
    public void save(List<E> member) throws PersistenceException  {
        try {
            fos = new FileOutputStream(location);
            oos = new ObjectOutputStream(fos);

            System.out.println( "LOG: Es wurden " + member.size() + " Member-Objekte wurden erfolgreich gespeichert!");
            oos.writeObject(member);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException(SaveFailure, "Fehler beim Speichern der Datei!");
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<E> load() throws PersistenceException  {
        List<E> list = null;

        try {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                list = (List) obj;
            }
            System.out.println("LOG: Es wurden " + list.size() + " User Stories erfolgreich reingeladen!");

            return list;

        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException(LoadFailure, "Fehler beim Laden der Datei!");

        } catch (ClassNotFoundException e) {
            throw new PersistenceException(LoadFailure, "Fehler beim Laden der Datei! Class not found!");
        }
    }
}
