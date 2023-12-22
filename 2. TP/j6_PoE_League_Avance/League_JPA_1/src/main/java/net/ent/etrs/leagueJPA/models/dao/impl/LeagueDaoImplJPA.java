package net.ent.etrs.leagueJPA.models.dao.impl;

import net.ent.etrs.leagueJPA.models.dao.LeagueDao;
import net.ent.etrs.leagueJPA.models.dao.exceptions.DaoException;
import net.ent.etrs.leagueJPA.models.entities.Challenge;
import net.ent.etrs.leagueJPA.models.entities.League;
import net.ent.etrs.leagueJPA.models.entities.references.LabySpecialite;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

public class LeagueDaoImplJPA extends JpaBaseDao<League> implements LeagueDao {
    @Override
    public Map<League, Long> getRewardPointsByLeague() {
        TypedQuery<Object[]> typedQuery = em.createQuery("SELECT l, SUM(c.rewardPoints) FROM League l JOIN l.challenges c GROUP BY l", Object[].class);

        List<Object[]> resultList = typedQuery.getResultList();
        Map<League, Long> result = new HashMap<>();

        for (Object[] row : resultList) {
            League league = (League) row[0];
            long points = (long) row[1];
            result.put(league, points);
        }

        return Collections.unmodifiableMap(result);
    }

    @Override
    public Set<LabySpecialite> topThreeBestBuild(League league) {
        TypedQuery<LabySpecialite> query = em.createQuery("SELECT DISTINCT p.build FROM Personnage p, League l JOIN l.classement WHERE l = :league ORDER BY p.level DESC", LabySpecialite.class).setMaxResults(3);

        Set<LabySpecialite> topThree = new HashSet<>(query.getResultList());

        return Collections.unmodifiableSet(topThree);
    }

    @Override
    public Map<League, Set<LabySpecialite>> bestBuildByLeague() {
        return null;
    }
}