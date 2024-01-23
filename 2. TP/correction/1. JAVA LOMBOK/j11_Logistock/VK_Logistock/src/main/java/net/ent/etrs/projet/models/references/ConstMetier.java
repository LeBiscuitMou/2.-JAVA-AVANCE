package net.ent.etrs.projet.models.references;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConstMetier {
    public static final String FORMATTER = "yyyy-MM-dd";
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(FORMATTER);
    public static final double PRIX_MIN_POUR_RISTOURNE = 1000.0;
    public static final double RISTOURNE_PRIX = 0.95;
    public static final double VIP_REQUIREMENT = 5000.0;
    public static final double RISTOURNE_VIP = 0.95;

    /* *********************************** MESSAGE ERREUR *********************************** */
    public static final String DATABASE_ACCESS = "une erreur est survenue lors de l'accès à la base de données ";
    public static final String ERROR_CREATION = "une erreur est survenue lors de la création de -> ";
    public static final String ERROR_SAVE = "une erreur est survenue lors de la sauvegarde de -> ";
    public static final String ERROR_DELETE = "une erreur est survenue lors de la suppression de -> ";
    public static final String ERROR_FIND = "une erreur est survenue lors de la récupérations des données -> ";
    public static final String ERROR_NULL = " doit être référencé";


    public static final String ERROR_COMMANDE_NUMERO_COMMANDE = "le numéro de commande doit s'écrire avec 4 chiffres décrivant l’année de la commande suivie d’un tiret suivi de 5 chiffres ";
}
