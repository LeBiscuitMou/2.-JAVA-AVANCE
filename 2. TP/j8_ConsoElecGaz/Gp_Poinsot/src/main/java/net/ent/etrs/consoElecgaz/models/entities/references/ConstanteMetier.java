package net.ent.etrs.consoElecgaz.models.entities.references;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConstanteMetier {

    public static final DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final String JOUEUR_METIER_NOM_VIDE = "Le nom du Joueur ne doit pas être vide";
    public static final String JOUEUR_METIER_NOM_NULL = "Le nom du Joueur ne doit pas être null";
    public static final String JOUEUR_METIER_PRENOM_VIDE = "Le prenom du Joueur ne doit pas être vide";
    public static final String JOUEUR_METIER_PRENOM_NULL = "Le prenom du Joueur ne doit pas être null";
    public static final String JOUEUR_METIER_DATE_NAISSANCE_PASSE = "La date de naissance doit être dans le passé ou à la date d'aujourd'hui";
    public static final String JOUEUR_METIER_DATE_NAISSANCE_NULL = "La date de naissance ne peut pas être null";
    public static final String JOUEUR_METIER_NBPOINT_POSITIF = "le nombre de point doit être positif ou égal à 0";
    public static final String EQUIPE_METIER_NOM_VIDE = "Le nom de l'équipe ne doit pas etre vide";
    public static final String EQUIPE_METIER_NOM_NULL = "Le nom de l'équipe ne doit pas etre null";
    public static final String EQUIPE_METIER_VILLE_NULL = "La ville ne doit pas etre null";
    public static final String MATCH_METIER_DATE_NULL = "La date du match ne doit pas etre null";
    public static final String MATCH_METIER_DATE_PAST = "la date du match ne doit pas etre dans le passé";
    public static final String MATCH_METIER_VILLE_NULL = "La ville ne doit pas etre null";
    public static final String CHAMPIONNAT_METIER_ANNEE_NULL = "L'annee ne doit pas etre null";
    public static final String CHAMPIONNAT_METIER_ANNEE_POSITIVE = "L'annee doit etre positif";
    public static final String CHAMPIONNAT_METIER_NOM_NULL = "Le nom du championnat ne doit pas etre null";
    public static final String CHAMPIONNAT_METIER_NOM_VIDE = "Le nom du championnat ne doit pas etre vide";
    public static final String JOUEUR_METIER_NBPOINT_NULL = "Le nombre de point ne doit pas etre null";
    public static final String MATCH_SCORE_NULL = "Le joueur ou les but ne peuvent pas etre null";
    public static final String JOUEUR_METIER_NOM_LENGTH = "Le nom du joueur doit etre au moins 3 caractères et max 50";
    public static final String JOUEUR_METIER_PRENOM_LENGTH = "Le prenom du joueur doit etre au moins 3 caractères et max 30";
    public static final String MATCH_METIER_VILLE_LENGTH = "La ville doit etre au max de 50 caractères";
    public static final String CHAMPIONNAT_METIER_NOM_LONGUEUR = "Le nom du championnat doit etre au moins 2 caractères et max 50";
    public static final String EQUIPE_METIER_NOM_LONGUEUR = "Le nom de l'équipe doit etre au moins 3 caractères et max 100";
    public static final String EQUIPE_METIER_POSITION_NULL = "La position ne doit pas etre null";
    public static final int NB_JOUEUR_MIN_PAR_EQUIPE = 11;
    public static final String EQUIPE_METIER_NB_JOUEUR_MIN = "Le nombre de joueurs doit etre au moins " + NB_JOUEUR_MIN_PAR_EQUIPE;
}
