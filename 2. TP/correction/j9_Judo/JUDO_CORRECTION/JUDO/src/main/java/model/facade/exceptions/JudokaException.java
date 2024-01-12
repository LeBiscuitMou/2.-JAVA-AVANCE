package model.facade.exceptions;

public class JudokaException extends Exception {
    public JudokaException() {
    }

    public JudokaException(String message) {
        super(message);
    }

    public JudokaException(String message, Throwable cause) {
        super(message, cause);
    }

    public JudokaException(Throwable cause) {
        super(cause);
    }

    public JudokaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
