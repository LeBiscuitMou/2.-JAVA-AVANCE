package net.ent.etrs.judo.models.references;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConstMetier {
    public static final String INIT_FILE_NAME = "";

    /* *********************************** MESSAGE ERREUR *********************************** */
    public static final String DATABASE_ACCESS = "une erreur est survenue lors de l'accès à la base de données ";
    public static final String ERROR_CREATION = "une erreur est survenue lors de la création de -> ";
    public static final String ERROR_SAVE = "une erreur est survenue lors de la sauvegarde de -> ";
    public static final String ERROR_DELETE = "une erreur est survenue lors de la suppression de -> ";
    public static final String ERROR_FIND = "une erreur est survenue lors de la récupérations des données -> ";
    public static final String ERROR_NULL = " doit être référencé";
}
