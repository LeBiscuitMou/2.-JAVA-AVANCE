package net.ent.etrs.poeleague.models.facades.impl;

import net.ent.etrs.poeleague.models.dao.DaoFactory;
import net.ent.etrs.poeleague.models.dao.DaoLeague;
import net.ent.etrs.poeleague.models.dao.exceptions.DaoException;
import net.ent.etrs.poeleague.models.entities.League;
import net.ent.etrs.poeleague.models.entities.references.LabySpecialite;
import net.ent.etrs.poeleague.models.facades.FacadeLeague;
import net.ent.etrs.poeleague.models.facades.exception.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FacadeLeagueImpl implements FacadeLeague {

    DaoLeague daoLeague;

    public FacadeLeagueImpl() {
        daoLeague = DaoFactory.getDaoLeague();
    }

    /**
     * Permet de sauvegarder une League
     *
     * @param league la league à sauvegarder
     */
    @Override
    public void saveLeague(League league) throws BusinessException {
        try {
            this.daoLeague.save(league);
        } catch (DaoException e) {
            throw new BusinessException("Impossible de sauvegarder la league", e);
        }

    }

    /**
     * Permet de supprimer une League
     *
     * @param league la league à supprimer
     */
    @Override
    public void deleteLeague(League league) throws BusinessException {
        try {
            this.daoLeague.delete(league);
        } catch (DaoException e) {
            throw new BusinessException("Impossible de supprimer la league", e);
        }
    }

    /**
     * Permet de récupérer toutes les leagues
     *
     * @return les leagues de la base
     */
    @Override
    public Set<League> findAllLeagues() throws BusinessException {
        try {
            return new HashSet<>(IterableUtils.toList(this.daoLeague.findAll()));
        } catch (DaoException e) {
            throw new BusinessException("Impossible de récupérer les leagues", e);
        }
    }

    /**
     * Permet de récupérer les rewards points par League
     *
     * @return une Map contenant la league et la somme de ses reward points.
     */
    @Override
    public Map<League, Long> findRewardPointsByLeague() throws BusinessException {
        try {
            return this.daoLeague.getRewardPointsByLeague();
        } catch (DaoException e) {
            throw new BusinessException("Impossible de récupérer les leagues avec leur points", e);
        }
    }

    /**
     * Permet de récupérer les 3 meilleurs builds de la league
     *
     * @param league league choisie
     * @return un Set des trois meilleurs build
     */
    @Override
    public Set<LabySpecialite> findTopThreeBestBuild(League league) {
        return daoLeague.topThreeBestBuild(league);
    }

    /**
     * Permet de connaître les 3 meilleurs builds pour chaque League
     *
     * @return une Map contenant la league ainsi que les 3 meilleurs builds.
     */
    @Override
    public Map<League, Set<LabySpecialite>> findBestBuildByLeague() throws BusinessException {
        try {
            return daoLeague.bestBuildByLeague();
        } catch (DaoException e) {
            throw new BusinessException("Impossible de récupérer les leagues avec leur best build", e);
        }
    }
}
