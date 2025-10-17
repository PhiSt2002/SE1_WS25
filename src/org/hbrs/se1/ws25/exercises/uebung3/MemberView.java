package org.hbrs.se1.ws25.exercises.uebung3;

import org.hbrs.se1.ws25.uebung2.Member;
import java.util.List;

/**
 * @author pstrun2s
 */

public class MemberView {

    public void dump(List<Member> liste) {
        for (Member m : liste) {
            System.out.println(m.toString());
        }
    }
}
