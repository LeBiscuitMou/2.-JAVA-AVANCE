package net.ent.etrs.poeleague.models.dao.impl;

import net.ent.etrs.poeleague.models.dao.DaoLeague;
import net.ent.etrs.poeleague.models.dao.base.JpaBaseDao;
import net.ent.etrs.poeleague.models.dao.exceptions.DaoException;
import net.ent.etrs.poeleague.models.entities.League;
import net.ent.etrs.poeleague.models.entities.references.LabySpecialite;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.Tuple;
import java.util.*;
import java.util.stream.Collectors;

public class DaoLeagueImpl extends JpaBaseDao<League> implements DaoLeague {
    @Override
    public Map<League, Long> getRewardPointsByLeague() throws DaoException {
        try {
            Map<League, Long> tp = this.em.createQuery(
                            "SELECT l as league, SUM(c.rewardPoints) as rewardpoints " +
                                    "FROM League l " +
                                    "INNER JOIN l.lesChallenges c " +
                                    "GROUP BY l", Tuple.class)
                    .getResultStream()
                    .collect(Collectors.toMap(
                            tuple -> ((League) tuple.get("league")),
                            tuple -> ((Long) tuple.get("rewardpoints"))
                    ));
            return tp;
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public Set<LabySpecialite> topThreeBestBuild(League league) {
        Query query = em.createNativeQuery(
                "SELECT p.build " +
                        "FROM db_poe.public.personnage p " +
                        "INNER JOIN db_poe.public.league_classement lc ON p.id = lc.personnage_id " +
                        "INNER JOIN db_poe.public.league league ON lc.league_id = league.id " +
                        "WHERE league.nom = :nomLeague " +
                        "ORDER BY lc.classement ASC");
        query.setParameter("nomLeague", league.getNom());
        Set<LabySpecialite> result = new HashSet<>();
        List list = query.getResultList();
        while (result.size() < 3) {
            result.add(LabySpecialite.valueOf((String) list.get(0)));
            list.remove(0);
        }
        return result;
    }

    @Override
    public Map<League, Set<LabySpecialite>> bestBuildByLeague() throws DaoException {
        Map<League, Set<LabySpecialite>> result = new HashMap<>();
        for (League l : this.findAll()) {
            result.put(l, this.topThreeBestBuild(l));
        }
        return result;
    }
}
