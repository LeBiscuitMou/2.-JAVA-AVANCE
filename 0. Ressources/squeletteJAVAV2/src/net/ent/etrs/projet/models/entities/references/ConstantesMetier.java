package net.ent.etrs.projet.models.entities.references;

import java.time.format.DateTimeFormatter;

public final class ConstantesMetier {

    public static final String AUTEUR_NOM_INCORRECT = "Le nom de l'auteur est incorrect";
    public static final String AUTEUR_PRENOM_INCORRECT = "le prénom de l'auteur est incorrect";
    public static final String JDS_NOM_INCORRECT = "Le nom du jeu de société est incorrect";
    public static final int NB_JOUEUR_MIN = 1;
    public static final String JDS_NB_JOUEUR_INCORRECT = "Le nombre de joueur ne peut pas être inférieur à " + NB_JOUEUR_MIN;
    public static final float PRIX_HT_MIN = 8.0f;
    public static final String JDS_PRIX_INCORRECT = "Le prix du jeu de société ne peujt pas être inférieur à " + PRIX_HT_MIN;
    public static final String JDS_AUTEUR_INCORRECT = "L'auteur du jeu de société est incorrect";
    public static final String ME_NOM_INCORRECT = "Le nom de la maisson d'édition est incorrect";
    public static final String ME_DATE_CREATION_FUTUR = "La date de création ne doit pas être dans le Futur";
    public static final String ME_DATE_CREATION_NULL = "La date de création ne doit pas être null";
    public static final String JDS_CATEGORIES_INCORRECT = "Les catégories sont null";
    public static final String ME_PAYS_NULL = "LE pays de la maison d'édition est null";
    public static final String JDS_NB_JOUEUR_INCOHERENCE = "Le nombre de joueur maximum ne doit pas être inférieur au nombre de joueur minimum";
    public static final String JDS_MAISON_EDITION_INCORRECT = "La maison d'edition est incorrecte";
    public static final String CONSTRUCTION_AUTEUR_ERROR = "Impossible de fabriquer l'Auteur";
    public static final String CONSTRUCTION_MAISON_ERROR = "Impossible de fabriquer la Maison d'Edition";
    public static final String CONSTRUCTION_JDS_ERROR = "Impossible de fabriquer le jeu de societe";
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final String INIT_ERROR = "Impossible d'initialiser";
    public static final String FACADE_JDS_ERROR_SAVE_DAO = "Impossible de sauvegarder le jeu de societe";
    public static final String FACADE_AUTEUR_ERROR_FIND_ALL = "Impossible de récupérer les auteurs";
    public static final String FACADE_JDS_ERROR_FIND_ALL = "Impossible de récupérer les jeux de société";
    public static final String FACADE_MAISON_EDITION_ERROR_FIND_ALL = "Impossible de récupérer les maison d'éditions";
    public static final String FACADE_AUTEUR_ERROR_SAVE_DAO = "Impossible de sauvegarder un Auteur";

    public static final String FACADE_MAISON_EDITION_ERROR_SAVE_DAO = "Impossible de sauvegarder une maison d'édition";


    private ConstantesMetier() {
    }


}
