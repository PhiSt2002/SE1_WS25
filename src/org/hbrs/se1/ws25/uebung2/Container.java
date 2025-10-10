package org.hbrs.se1.ws25.uebung2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pstrun2s
 */

public class Container {

    private List<Member> members = new ArrayList<>();

    public void addMember(Member member) throws ContainerException {
        for (Member m : members) {
            if (m.getID().equals(member.getID())) {
                throw new ContainerException(member.getID());
            }
        }
        members.add(member);
    }

    public String deleteMember(Integer id) {
        for (Member m : members) {
            if (m.getID().equals(id)) {
                members.remove(m);
                return "Member mit ID " + id + " wurde gelöscht.";
            }
        }
        return "Kein Member mit ID " + id + " vorhanden.";
    }

    public void dump() {
        for (Member m : members) {
            System.out.println(m.toString());
        }
    }

    public int size() {
        return members.size();
    }
}
