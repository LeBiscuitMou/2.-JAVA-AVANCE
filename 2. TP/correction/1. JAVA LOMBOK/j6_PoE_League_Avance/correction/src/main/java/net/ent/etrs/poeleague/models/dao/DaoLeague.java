package net.ent.etrs.poeleague.models.dao;

import net.ent.etrs.poeleague.models.dao.base.BaseDao;
import net.ent.etrs.poeleague.models.dao.exceptions.DaoException;
import net.ent.etrs.poeleague.models.entities.League;
import net.ent.etrs.poeleague.models.entities.references.LabySpecialite;

import java.util.Map;
import java.util.Set;

public interface DaoLeague extends BaseDao<League> {
    Map<League, Long> getRewardPointsByLeague() throws DaoException;

    Set<LabySpecialite> topThreeBestBuild(League league);

    Map<League, Set<LabySpecialite>> bestBuildByLeague() throws DaoException;
}
