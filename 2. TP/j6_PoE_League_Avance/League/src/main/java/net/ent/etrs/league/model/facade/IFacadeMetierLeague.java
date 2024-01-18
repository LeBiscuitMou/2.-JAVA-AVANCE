package net.ent.etrs.league.model.facade;

import net.ent.etrs.league.model.entities.League;
import net.ent.etrs.league.model.entities.references.LabySpecialite;

import java.util.Map;
import java.util.Set;

public interface IFacadeMetierLeague {
    /**
     * Permet de sauvegarder une League
     *
     * @param league la league à sauvegarder
     */
    void saveLeague(League league);

    /**
     * Permet de supprimer une League
     *
     * @param league la league à supprimer
     */
    void deleteLeague(League league);

    /**
     * Permet de récupérer toutes les leagues
     *
     * @return les leagues de la base
     */
    Set<League> findAllLeagues();

    /**
     * Permet de récupérer les rewards points par League
     *
     * @return une Map contenant la league et la somme de ses reward points.
     */
    Map<League, Integer> GetRewardPointsByLeague();

    /**
     * Permet de récupérer les 3 meilleurs builds de la league
     *
     * @param league league choisie
     * @return un Set des trois meilleurs build
     */
    Set<LabySpecialite> topThreeBestBuild(League league);

    /**
     * Permet de connaître les 3 meilleurs builds pour chaque League
     *
     * @return une Map contenant la league ainsi que les 3 meilleurs builds.
     */
    Map<League, Set<LabySpecialite>> bestBuildByLeague();
}
