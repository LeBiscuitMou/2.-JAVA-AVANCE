package net.ent.etrs.cycliste.model.entities.exceptions;

public class CyclisteRuntimeException extends RuntimeException {
    public CyclisteRuntimeException(String epreuveIsNull) {
        super(epreuveIsNull);
    }
}
