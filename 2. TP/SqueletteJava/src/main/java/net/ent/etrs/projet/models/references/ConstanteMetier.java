package net.ent.etrs.projet.models.references;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConstanteMetier {
    public static final String ERROR_CREATION = "une erreur est survenue lors de la création de -> ";
    public static final String ERROR_SAVE = "une erreur est survenue lors de la sauvegarde de -> ";
    public static final String ERROR_UPDATE = "une erreur est survenue lors de la modification de -> ";
    public static final String ERROR_DELETE = "une erreur est survenue lors de la suppression de -> ";
    public static final String DATABASE_ACCESS = "une erreur est survenue lors de l'accès à la base de données ";
    public static final String ERROR_NULL = " doit être référencé";
    public static final String ERROR_BLANK = " ne peut pas être vide";
    public static final String ERROR_FUTUR = " ne peut pas être dans le futur";
    public static final String ERROR_PAST = " ne peut pas être dans le passé";
    public static final String ERROR_MIN = " ne peux pas être plus petit que ";
    public static final String ERROR_MAX = " ne peux pas être plus grand que ";
}
