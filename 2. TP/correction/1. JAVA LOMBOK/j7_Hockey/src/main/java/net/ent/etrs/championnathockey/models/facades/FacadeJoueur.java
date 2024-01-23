package net.ent.etrs.championnathockey.models.facades;

import net.ent.etrs.championnathockey.models.entities.Joueur;
import net.ent.etrs.championnathockey.models.facades.exceptions.BusinessException;

import java.time.LocalDate;
import java.util.Set;

public interface FacadeJoueur {
    /**
     * Permet de sauvegarder un joueur
     * @param joueur (Joueur) joueur a sauvegarder
     * @throws BusinessException si le joueur n'est pas valide
     */
    void saveJoueur(Joueur joueur) throws BusinessException;

    /**
     * Permet de supprimer un joueur
     * @param idJoueur (Long) identifiant base de donnée du joueur à supprimer
     * @throws BusinessException si l'identifiant est invalide
     */
    void deleteJoueur(Long idJoueur) throws BusinessException;

    /**
     * Permet de recuperer tous les joueurs
     * @return un Set de tous les joueurs de la base de donnée
     * @throws BusinessException s'il y a un soucis avec la base de donnée
     */
    Set<Joueur> findAllJoueurs() throws BusinessException;

    /**
     * Permet de recupérer tous les joueur nés avant la date
     * @param date (LocaleDate) date de laquelle rechercher les joueurs
     * @return un Set de tous les joueurs nés avant la date
     */
    Set<Joueur> findAllJoueurBirthBefore(LocalDate date) throws BusinessException;

    /**
     * Permet de récupérer le joueur qui a marqué le plus de but
     * @return (Joueur) le joueur qui a marqué le plus de but
     * @throws BusinessException s'il y a un soucis avec la base de donnée'
     */
    Joueur findJoueurBestScoreur() throws BusinessException;
}
