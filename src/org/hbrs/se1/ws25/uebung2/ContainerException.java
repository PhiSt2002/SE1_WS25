package org.hbrs.se1.ws25.uebung2;

/**
 * @author pstrun2s
 */

public class ContainerException extends Exception {

    public ContainerException(Integer id) {
        super("Das Member-Objekt mit der ID " + id + " ist bereits vorhanden!");
    }

}
