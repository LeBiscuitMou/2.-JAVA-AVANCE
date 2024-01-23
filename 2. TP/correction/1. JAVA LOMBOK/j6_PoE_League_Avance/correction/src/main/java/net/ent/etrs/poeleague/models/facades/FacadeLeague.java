package net.ent.etrs.poeleague.models.facades;

import net.ent.etrs.poeleague.models.dao.exceptions.DaoException;
import net.ent.etrs.poeleague.models.entities.League;
import net.ent.etrs.poeleague.models.entities.references.LabySpecialite;
import net.ent.etrs.poeleague.models.facades.exception.BusinessException;

import java.util.Map;
import java.util.Set;

public interface FacadeLeague {
    /**
     * Permet de sauvegarder une League
     *
     * @param league la league à sauvegarder
     */
    void saveLeague(League league) throws BusinessException;


    /**
     * Permet de supprimer une League
     *
     * @param league la league à supprimer
     */
    void deleteLeague(League league) throws BusinessException;

    /**
     * Permet de récupérer toutes les leagues
     *
     * @return les leagues de la base
     */
    Set<League> findAllLeagues() throws BusinessException;

    /**
     * Permet de récupérer les rewards points par League
     *
     * @return une Map contenant la league et la somme de ses reward points.
     */
    Map<League, Long> findRewardPointsByLeague() throws DaoException, BusinessException;

    /**
     * Permet de récupérer les 3 meilleurs builds de la league
     *
     * @param league league choisie
     * @return un Set des trois meilleurs build
     */
    Set<LabySpecialite> findTopThreeBestBuild(League league);

    /**
     * Permet de connaître les 3 meilleurs builds pour chaque League
     *
     * @return une Map contenant la league ainsi que les 3 meilleurs builds.
     */
    Map<League, Set<LabySpecialite>> findBestBuildByLeague() throws BusinessException;
}
