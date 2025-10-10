package org.hbrs.se1.ws25.uebung2.test;

import org.hbrs.se1.ws25.uebung2.ConcreteMember;
import org.hbrs.se1.ws25.uebung2.Container;
import org.hbrs.se1.ws25.uebung2.ContainerException;
import org.hbrs.se1.ws25.uebung2.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author pstrun2s
 */

public class ContainerTest {

    private Container container;

    @BeforeEach
    public void setup() {
        container = new Container();
    }

    @Test
    public void testAddSingleMember() throws ContainerException {
        Member m1 = new ConcreteMember(1);
        container.addMember(m1);
        assertEquals(1, container.size());
    }

    @Test
    public void testAddDuplicateMemberThrowsException() throws ContainerException {
        Member m1 = new ConcreteMember(1);
        container.addMember(m1);
        ContainerException thrown = assertThrows(ContainerException.class, () -> {
            container.addMember(new ConcreteMember(1));
        });
        assertEquals("Das Member-Objekt mit der ID 1 ist bereits vorhanden!", thrown.getMessage());
    }

    @Test
    public void testDeleteExistingMember() throws ContainerException {
        Member m1 = new ConcreteMember(1);
        container.addMember(m1);
        String result = container.deleteMember(1);
        assertEquals("Member mit ID 1 wurde gelöscht.", result);
        assertEquals(0, container.size());
    }

    @Test
    public void testDeleteNonExistingMember() {
        String result = container.deleteMember(99);
        assertEquals("Kein Member mit ID 99 vorhanden.", result);
    }

    @Test
    public void testDumpOutput() throws ContainerException {
        Member m1 = new ConcreteMember(1);
        Member m2 = new ConcreteMember(2);
        container.addMember(m1);
        container.addMember(m2);
        assertEquals(2, container.size());
    }

    @Test
    public void testSizeAfterMultipleAdditions() throws ContainerException {
        for (int i = 1; i <= 5; i++) {
            container.addMember(new ConcreteMember(i));
        }
        assertEquals(5, container.size());
    }

    @Test
    public void testSizeAfterAddAndDelete() throws ContainerException {
        container.addMember(new ConcreteMember(1));
        container.addMember(new ConcreteMember(2));
        container.deleteMember(1);
        assertEquals(1, container.size());
    }

    @Test
    public void testAddMultipleUniqueMembers() throws ContainerException {
        for (int i = 10; i <= 15; i++) {
            container.addMember(new ConcreteMember(i));
        }
        assertEquals(6, container.size());
    }

    @Test
    public void testToStringFormat() {
        Member m = new ConcreteMember(42);
        assertEquals("Member (ID = 42)", m.toString());
    }

    @Test
    public void testAddDeleteAddAgain() throws ContainerException {
        Member m = new ConcreteMember(7);
        container.addMember(m);
        container.deleteMember(7);
        container.addMember(m); // Sollte wieder möglich sein
        assertEquals(1, container.size());
    }

}
