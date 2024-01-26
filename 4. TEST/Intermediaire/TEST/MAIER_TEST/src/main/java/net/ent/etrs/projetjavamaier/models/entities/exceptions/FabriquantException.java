package net.ent.etrs.projetjavamaier.models.entities.exceptions;

public class FabriquantException extends Exception {
    public FabriquantException(String message) {
        super(message);
    }

    public FabriquantException(String message, Throwable cause) {
        super(message, cause);
    }

    public FabriquantException(Throwable cause) {
        super(cause);
    }

    public FabriquantException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
