package model.facade.exceptions;

public class ClubException extends Exception {
    public ClubException() {
    }

    public ClubException(String message) {
        super(message);
    }

    public ClubException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClubException(Throwable cause) {
        super(cause);
    }

    public ClubException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
