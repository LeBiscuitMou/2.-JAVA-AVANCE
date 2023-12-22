package net.ent.etrs.leagueJPA.models.facades.impl;

import net.ent.etrs.leagueJPA.models.dao.ChallengeDao;
import net.ent.etrs.leagueJPA.models.dao.DaoFactory;
import net.ent.etrs.leagueJPA.models.dao.LeagueDao;
import net.ent.etrs.leagueJPA.models.dao.PersonnageDao;
import net.ent.etrs.leagueJPA.models.dao.exceptions.DaoException;
import net.ent.etrs.leagueJPA.models.entities.Challenge;
import net.ent.etrs.leagueJPA.models.entities.League;
import net.ent.etrs.leagueJPA.models.entities.Personnage;
import net.ent.etrs.leagueJPA.models.entities.references.LabySpecialite;
import net.ent.etrs.leagueJPA.models.facades.FacadeMetier;
import net.ent.etrs.leagueJPA.models.facades.exception.BusinessException;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public class FacadeMetierImpl implements FacadeMetier {
    ChallengeDao challengeDao = DaoFactory.getDaoChallenge();
    LeagueDao leagueDao = DaoFactory.getDaoLeague();
    PersonnageDao personnageDao = DaoFactory.getDaoPersonnage();

    /**
     * Permet de sauvegarder un personnage
     *
     * @param personnage le personnage à sauvegarder
     */
    @Override
    public void savePersonnage(Personnage personnage) throws BusinessException {
        try {
            this.personnageDao.save(personnage);
        } catch (DaoException | SQLException e) {
            throw new BusinessException("Impossible de sauvegarder le personnage", e);
        }
    }

    /**
     * Permet de sauvegarder un challenge
     *
     * @param challenge le challenge à sauvegarder
     */
    @Override
    public void saveChallenge(Challenge challenge) throws BusinessException {
        try {
            this.challengeDao.save(challenge);
        } catch (DaoException | SQLException e) {
            throw new BusinessException("Impossible de sauvegarder le challenge", e);
        }
    }

    /**
     * Permet de sauvegarder une League
     *
     * @param league la league à sauvegarder
     */
    @Override
    public void saveLeague(League league) throws BusinessException {
        try {
            this.leagueDao.save(league);
        } catch (DaoException | SQLException e) {
            throw new BusinessException("Impossible de sauvegarder la league", e);
        }
    }

    /**
     * Permet de supprimer un personnage
     *
     * @param personnage le personnage à supprimer
     */
    @Override
    public void deletePersonnage(Personnage personnage) throws BusinessException {
        try {
            if (personnageDao.exists(personnage.getId())) {
                personnageDao.delete(personnage.getId());
            }
        } catch (DaoException e) {
            throw new BusinessException("Impossible de supprimer personnage", e);
        }
    }

    /**
     * Permet de supprimer un challenge
     *
     * @param challenge le challenge à supprimer
     */
    @Override
    public void deleteChallenge(Challenge challenge) throws BusinessException {
        try {
            if (challengeDao.exists(challenge.getId())) {
                challengeDao.delete(challenge.getId());
            }
        } catch (DaoException e) {
            throw new BusinessException("Impossible de supprimer challenge", e);
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
            if (leagueDao.exists(league.getId())) {
                leagueDao.delete(league.getId());
            }
        } catch (DaoException e) {
            throw new BusinessException("Impossible de supprimer league", e);
        }
    }

    /**
     * Permet de récupérer tous les personnages
     *
     * @return les personnages de la base
     */
    @Override
    public Set<Personnage> findAllPersonnages() {
        return null;
    }

    /**
     * Permet de récupérer tous les challenges
     *
     * @return les challenges de la base
     */
    @Override
    public Set<Challenge> findAllChallenges() {
        return null;
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
     * Permet de récupérer le challenge qui rapport le plus de points
     *
     * @return le challenge qui possède le plus de points
     */
    @Override
    public Challenge getBestChallenge() throws BusinessException {
        try {
            return this.challengeDao.getBestChallenge().orElseThrow();
        } catch (DaoException e) {
            throw new BusinessException("Impossible", e);
        }
    }

    /**
     * Permet de récupérer les rewards points par League
     *
     * @return une Map contenant la league et la somme de ses reward points.
     */
    @Override
    public Map<League, Long> GetRewardPointsByLeague() throws BusinessException {
        try {
            return this.leagueDao.getRewardPointsByLeague();
        } catch (DaoException e) {
            throw new BusinessException("Impossible de récupérer les points par league", e);
        }
    }

    /**
     * Permet de récupérer les 3 meilleurs builds de la league
     *
     * @param league league choisie
     * @return un Set des trois meilleurs build
     */
    @Override
    public Set<LabySpecialite> topThreeBestBuild(League league) {
        return this.leagueDao.topThreeBestBuild(league);
    }

    /**
     * Permet de connaître les 3 meilleurs builds pour chaque League
     *
     * @return une Map contenant la league ainsi que les 3 meilleurs builds.
     */
    @Override
    public Map<League, Set<LabySpecialite>> bestBuildByLeague() {
        return this.leagueDao.bestBuildByLeague();
    }
}