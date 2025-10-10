package org.hbrs.se1.ws25.uebung2;

/**
 * @author pstrun2s
 */

public class ConcreteMember implements Member {

    private Integer id;

    public ConcreteMember(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public String toString() {
        return "Member (ID = " + id + ")";
    }
}
