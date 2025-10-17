package org.hbrs.se1.ws25.exercises.uebung3;

import org.hbrs.se1.ws25.exercises.uebung3.persistence.*;
import org.hbrs.se1.ws25.uebung2.ContainerException;
import org.hbrs.se1.ws25.uebung2.Member;

import java.util.ArrayList;
import java.util.List;

import static org.hbrs.se1.ws25.exercises.uebung3.persistence.PersistenceException.ExceptionType.*;

/**
 * @author pstrun2s
 */

public class Container {

    private static Container instance = null;
    private List<Member> members = new ArrayList<>();

    private PersistenceStrategy<Member> strategy;

    private Container() {}

    public static Container getInstance() {
        if (instance == null) {
            instance = new Container();
        }
        return instance;
    }

    public void setPersistenceStrategy(PersistenceStrategy<Member> strategy) {
        this.strategy = strategy;
    }

    public void store() throws PersistenceException {
        if (strategy == null) {
            throw new PersistenceException(ImplementationNotAvailable, "Keine Persistence-Strategie gesetzt!");
        }
        strategy.save(this.members);
    }

    public void load() throws PersistenceException {
        if (strategy == null) {
            throw new PersistenceException(ImplementationNotAvailable, "Keine Persistence-Strategie gesetzt!");
        }
        this.members = strategy.load();
    }

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

    public int size() {
        return members.size();
    }

    public List<Member> getCurrentList() {
        return this.members;
    }

    public void deleteAllMembers() {
        members.clear();
    }
}
