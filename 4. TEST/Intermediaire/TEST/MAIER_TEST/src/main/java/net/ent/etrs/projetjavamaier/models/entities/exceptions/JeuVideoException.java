package net.ent.etrs.projetjavamaier.models.entities.exceptions;

public class JeuVideoException extends Exception {
    public JeuVideoException(String message) {
        super(message);
    }

    public JeuVideoException(String message, Throwable cause) {
        super(message, cause);
    }

    public JeuVideoException(Throwable cause) {
        super(cause);
    }

    public JeuVideoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
