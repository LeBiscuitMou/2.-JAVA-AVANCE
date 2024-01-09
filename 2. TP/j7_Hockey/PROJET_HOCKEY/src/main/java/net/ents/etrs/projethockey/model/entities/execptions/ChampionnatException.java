package net.ents.etrs.projethockey.model.entities.execptions;

public class ChampionnatException extends Exception {
    public ChampionnatException(String message) {
        super(message);
    }

    public ChampionnatException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChampionnatException(Throwable cause) {
        super(cause);
    }

    public ChampionnatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
