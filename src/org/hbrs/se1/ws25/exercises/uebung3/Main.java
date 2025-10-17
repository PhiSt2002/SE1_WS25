package org.hbrs.se1.ws25.exercises.uebung3;

import org.hbrs.se1.ws25.uebung2.*;

/**
 * @author pstrun2s
 */

public class Main {

    public static void main(String[] args) {
        Container container = Container.getInstance();

        try {
            // Zwei Member erzeugen
            Member m1 = new ConcreteMember(1);
            Member m2 = new ConcreteMember(2);

            // Hinzufügen
            container.addMember(m1);
            container.addMember(m2);

            // View aufrufen
            MemberView view = new MemberView();
            view.dump(container.getCurrentList());

        } catch (ContainerException e) {
            System.out.println("[FEHLER] " + e.getMessage());
        }
    }

}
