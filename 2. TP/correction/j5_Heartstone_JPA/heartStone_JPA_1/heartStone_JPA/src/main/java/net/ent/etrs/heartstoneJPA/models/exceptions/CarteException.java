package net.ent.etrs.heartstoneJPA.models.exceptions;

public class CarteException extends Exception {
    public CarteException(String message) {
        super(message);
    }

    public CarteException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarteException(Throwable cause) {
        super(cause);
    }

    public CarteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
