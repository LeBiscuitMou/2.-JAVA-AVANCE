package net.ent.etrs.championnathockey.models.daos;

import net.ent.etrs.championnathockey.models.daos.exception.DaoException;
import net.ent.etrs.championnathockey.models.entities.Equipe;
import net.ent.etrs.championnathockey.models.entities.Joueur;
import net.ent.etrs.championnathockey.models.facades.exceptions.BusinessException;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface DaoEquipe extends BaseDao<Equipe, Serializable> {

    /**
     * Permet de retourner la liste des équipes d'une année de championnat
     * @param annee Année de Championnat
     * @return Liste des équipes
     * @throws DaoException s'il y a eu un problème
     */
    Set<Equipe> findAllEquipeByYearAndName(Integer annee, String nomChampionnat) throws DaoException;

    /**
     * Permet de trouver la meilleur equipe d'une Année de Championnat
     * @param annee Année de Championnat
     * @return Soit l'équipe soit Null si aucun équipe n'a été trouvé
     * @throws DaoException s'il y a eu un problème
     */
    Optional<Equipe> findBestEquipeByYearAndName(Integer annee, String nomChampionnat) throws DaoException;

    /**
     * Permet de retourner le dictionnaire des meilleurs joueurs par Equipe d'une Année de Championnat
     * @param annee Année de Championnat
     * @return une Map<Equipe, Joueur> contenant le meilleur joueur par Equipe pour l'année de championnat
     */
    Map<Equipe, Joueur> findBestJoueurByTeamForYearAndName(Integer annee, String nomChampionnat) throws DaoException;

    Optional<Equipe> findByName(String name);
}
