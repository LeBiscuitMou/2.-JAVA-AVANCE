package model.exceptions;

public class GradeMinimumAgeException extends Exception {

    public GradeMinimumAgeException() {
    }

    public GradeMinimumAgeException(String message) {
        super(message);
    }

    public GradeMinimumAgeException(String message, Throwable cause) {
        super(message, cause);
    }

    public GradeMinimumAgeException(Throwable cause) {
        super(cause);
    }

    public GradeMinimumAgeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
