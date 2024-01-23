package net.ent.etrs.championnathockey.models.facades;

import net.ent.etrs.championnathockey.models.entities.Equipe;
import net.ent.etrs.championnathockey.models.entities.Joueur;
import net.ent.etrs.championnathockey.models.facades.exceptions.BusinessException;

import java.util.Map;
import java.util.Set;

public interface FacadeEquipe {
    /**
     * Permet de sauvegarder une equipe
     * @param equipe (Equipe) equipe à sauvegarder
     * @throws BusinessException s'il y a eu un problème avec la sauvegarde de l'équipe
     */
    void saveEquipe(Equipe equipe) throws BusinessException;
    /**
     * Permet de supprimer une equipe
     * @param idEquipe (Long) id de l'équipe à supprimer
     * @throws BusinessException s'il y a eu un problème avec la suppression de l'équipe
     */
    void deleteEquipe(Long idEquipe) throws BusinessException;
    /**
     * Permet de recuperer toutes les equipes
     * @return un Set des Equipes de la base de données
     * @throws BusinessException s'il y a eu un problème avec la récupération des équipes
     */
    Set<Equipe> findAllEquipes() throws BusinessException;

    /**
     * Permet de recuperer l'équipe par son nom
     * @param name nom de l'équipe
     * @return l'équipe correspondant au nom
     * @throws BusinessException
     */
    Equipe findEquipeByName(String name) throws BusinessException;

    /**
     * Permet de recuperer les équipe d'une année de championnat
     * @param annee (Integer) année de championnat
     * @return un Set des Equipes de cette année de championnat
     */
    Set<Equipe> findAllEquipeByYearAndName(Integer annee, String nomChampionnat) throws BusinessException;

    /**
     * Permet de réupérer l'équipe qui a marqué le plus de point pour cette année
     * @param annee (Integer) année de championnat
     * @return la meilleur équipe de l'année de championnat
     */
    Equipe findBestEquipeByYearAndName(Integer annee, String nomChampionnat) throws BusinessException;

    /**
     * Permet de récupérer le meilleur joueur par équipe
     * @return une Map<Equipe, Joueur> du meilleur joueur par équipe
     */
    Map<Equipe, Joueur> findBestJoueurByTeamForYearAndName(Integer annee, String nomChampionnat) throws BusinessException;
}
