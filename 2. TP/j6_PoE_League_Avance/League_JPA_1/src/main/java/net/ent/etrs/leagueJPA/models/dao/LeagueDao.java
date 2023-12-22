package net.ent.etrs.leagueJPA.models.dao;

import net.ent.etrs.leagueJPA.models.dao.exceptions.DaoException;
import net.ent.etrs.leagueJPA.models.entities.League;
import net.ent.etrs.leagueJPA.models.entities.references.LabySpecialite;

import java.util.Map;
import java.util.Set;

public interface LeagueDao extends BaseDao<League> {
    Map<League, Long> getRewardPointsByLeague() throws DaoException;

    Set<LabySpecialite> topThreeBestBuild(League league);

    Map<League, Set<LabySpecialite>> bestBuildByLeague();
}
