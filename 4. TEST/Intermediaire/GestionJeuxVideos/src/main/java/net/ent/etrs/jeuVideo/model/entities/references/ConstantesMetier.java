package net.ent.etrs.jeuVideo.model.entities.references;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConstantesMetier {
    public static final String FORMATTER = "dd/MM/yyyy";
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(FORMATTER);

    /* *********************************** MESSAGE ERREUR *********************************** */
    public static final String DATABASE_ACCESS = "une erreur est survenue lors de l'accès à la base de données ";
    public static final String ERROR_CREATION = "une erreur est survenue lors de la création de ";
    public static final String ERROR_SAVE = "une erreur est survenue lors de la sauvegarde de ";
    public static final String ERROR_FIND = "une erreur est survenue lors de la récupérations des données de ";
    public static final String PLATEFORME_NULL = "La plateforme doit être référencé";
    public static final String PAYS_NULL = "Le pays ne doit pas être null";
    public static final String DATE_SORTIE_NULL = "la date de sortie ne doit pas être null";
}
