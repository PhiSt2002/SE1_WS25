package org.hbrs.se1.ws25.tests.uebung3;

import org.hbrs.se1.ws25.uebung2.ConcreteMember;
import org.hbrs.se1.ws25.uebung2.ContainerException;
import org.hbrs.se1.ws25.uebung2.Member;
import org.hbrs.se1.ws25.exercises.uebung3.*;
import org.hbrs.se1.ws25.exercises.uebung3.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hbrs.se1.ws25.exercises.uebung3.persistence.PersistenceException.ExceptionType.*;
import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    private Container container;

    @BeforeEach
    public void setup() {
        container = Container.getInstance();
        container.deleteAllMembers(); // Sauberer Start
    }

    @Test
    void testMongoDBNotImplementedSolution() {
        container.setPersistenceStrategy(new PersistenceStrategyMongoDB<>());
        PersistenceException thrown = assertThrows(PersistenceException.class, () -> {
            container.store();
        });
        assertTrue(thrown.getMessage().contains("MongoDB nicht implementiert"));


        // Set a strategy, which has not been implemented
        // container.setPersistenceStrategie( new PersistenceStrategyMongoDB<Member>() );

        // Testing store
        // Hinweis: Beim Aufruf der Methoden store() oder load() muss eine Exception vom Typ
        // PersistenceException zurückgegeben werden. Der ExceptionType lautet ImplementationNotAvailable
        // Die Message (abrufbar mit der Methode e.getMessage() ) muss einen eindeutigen Text haben, z.B.:
        // assertEquals( e.getMessage() , "MongoDB is not implemented!"  );
    }

    @Test
    void testNoStrategeySet() {
        container.setPersistenceStrategy(null);
        PersistenceException thrown = assertThrows(PersistenceException.class, () -> {
            container.store();
        });
        assertTrue(thrown.getMessage().contains("Keine Persistence-Strategie"));
    }

    @Test
    void testWrongLocationOfFile() {
        PersistenceStrategyStream<Member> strat = new PersistenceStrategyStream<>();
        strat.setLocation("/Users/philippstrunk/tmp"); // Directory statt Datei
        container.setPersistenceStrategy(strat);

        Member m = new ConcreteMember(1);
        try {
            container.addMember(m);
        } catch (ContainerException e) {
            fail("Setup fehlgeschlagen: " + e.getMessage());
        }

        PersistenceException thrown = assertThrows(PersistenceException.class, () -> {
            container.store();
        });

        assertTrue(thrown.getMessage().contains("Speichern fehlgeschlagen"));
    }

    @Test
    void testStoreDeleteAndLoad() {
        try {
            // 1. Alles löschen
            container.deleteAllMembers();
            assertEquals(0, container.size());

            // 2. Strategie setzen
            PersistenceStrategyStream<Member> strategy = new PersistenceStrategyStream<>();
            strategy.setLocation("test_member.ser");
            container.setPersistenceStrategy(strategy);

            // 3. Member hinzufügen
            Member m = new ConcreteMember(42);
            container.addMember(m);
            assertEquals(1, container.size());

            // 4. Abspeichern
            container.store();

            // 5. Löschen
            container.deleteAllMembers();
            assertEquals(0, container.size());

            // 6. Laden
            container.load();
            assertEquals(1, container.size());
            assertEquals(42, container.getCurrentList().get(0).getID());

        } catch (Exception e) {
            fail("RoundTrip-Test fehlgeschlagen: " + e.getMessage());
        }
    }

}