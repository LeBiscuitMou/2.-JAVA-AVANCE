package net.ent.etrs.league.model.facade.impl;

import net.ent.etrs.league.model.dao.impl.DaoFactory;
import net.ent.etrs.league.model.dao.IDaoPersonnage;
import net.ent.etrs.league.model.entities.League;
import net.ent.etrs.league.model.entities.references.LabySpecialite;
import net.ent.etrs.league.model.facade.IFacadeMetierLeague;

import java.util.Map;
import java.util.Set;

public class FacadeMetierLeagueImpl implements IFacadeMetierLeague {
    private IDaoPersonnage daoExemple;

    protected FacadeMetierLeagueImpl() {
        daoExemple = DaoFactory.getDaoExemple();
    }

    /**
     * Permet de sauvegarder une League
     *
     * @param league la league à sauvegarder
     */
    @Override
    public void saveLeague(League league) {

    }

    /**
     * Permet de supprimer une League
     *
     * @param league la league à supprimer
     */
    @Override
    public void deleteLeague(League league) {

    }

    /**
     * Permet de récupérer toutes les leagues
     *
     * @return les leagues de la base
     */
    @Override
    public Set<League> findAllLeagues() {
        return null;
    }

    /**
     * Permet de récupérer les rewards points par League
     *
     * @return une Map contenant la league et la somme de ses reward points.
     */
    @Override
    public Map<League, Integer> GetRewardPointsByLeague() {
        return null;
    }

    /**
     * Permet de récupérer les 3 meilleurs builds de la league
     *
     * @param league league choisie
     * @return un Set des trois meilleurs build
     */
    @Override
    public Set<LabySpecialite> topThreeBestBuild(League league) {
        return null;
    }

    /**
     * Permet de connaître les 3 meilleurs builds pour chaque League
     *
     * @return une Map contenant la league ainsi que les 3 meilleurs builds.
     */
    @Override
    public Map<League, Set<LabySpecialite>> bestBuildByLeague() {
        return null;
    }
}