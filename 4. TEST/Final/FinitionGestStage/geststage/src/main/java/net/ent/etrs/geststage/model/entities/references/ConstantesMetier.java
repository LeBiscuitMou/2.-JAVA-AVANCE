package net.ent.etrs.geststage.model.entities.references;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConstantesMetier {
    public static final String FORMATTER = "yyyy-MM-dd";
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(FORMATTER);

    /* *********************************** MESSAGE ERREUR *********************************** */
    public static final String DATABASE_ACCESS = "une erreur est survenue lors de l'accès à la base de données ";
    public static final String ERROR_CREATION = "une erreur est survenue lors de la création de ";
    public static final String ERROR_SAVE = "une erreur est survenue lors de la sauvegarde de ";
    public static final String ERROR_DELETE = "une erreur est survenue lors de la suppression de ";
    public static final String ERROR_FIND = "une erreur est survenue lors de la récupérations des données ";
    public static final String ERROR_NULL = " doit être référencé";
    public static final String STAGIAIRE_NULL = "Le stagiaire ne peut pas être null";
    public static final String STAGIAIRE_NOT_EXIST = "Le stagiaire n'existe pas dans la liste";
    public static final String STAGIAIRE_MATIERE_NOTE__NULL = "Les notes et les matières ne peuvent pas être null";
}
