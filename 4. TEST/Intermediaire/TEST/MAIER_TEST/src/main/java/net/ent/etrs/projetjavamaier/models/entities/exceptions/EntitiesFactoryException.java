package net.ent.etrs.projetjavamaier.models.entities.exceptions;

public class EntitiesFactoryException extends Exception {
    public EntitiesFactoryException(String message) {
        super(message);
    }

    public EntitiesFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntitiesFactoryException(Throwable cause) {
        super(cause);
    }

    public EntitiesFactoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
