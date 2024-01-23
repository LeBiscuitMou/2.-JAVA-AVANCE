package model.facade.exceptions;

public class CompetitionException extends Exception {
    public CompetitionException() {
    }

    public CompetitionException(String message) {
        super(message);
    }

    public CompetitionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompetitionException(Throwable cause) {
        super(cause);
    }

    public CompetitionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
