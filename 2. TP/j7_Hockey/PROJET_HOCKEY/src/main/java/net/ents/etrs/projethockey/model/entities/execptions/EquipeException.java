package net.ents.etrs.projethockey.model.entities.execptions;

public class EquipeException extends Exception {
    public EquipeException(String message) {
        super(message);
    }

    public EquipeException(String message, Throwable cause) {
        super(message, cause);
    }

    public EquipeException(Throwable cause) {
        super(cause);
    }

    public EquipeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
