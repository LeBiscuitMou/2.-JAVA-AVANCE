package net.ent.etrs.league.model.dao;

import net.ent.etrs.league.model.dao.base.BaseDao;
import net.ent.etrs.league.model.dao.exceptions.DaoException;
import net.ent.etrs.league.model.entities.Challenge;

import java.util.Optional;

public interface IDaoChallenge extends BaseDao<Challenge> {
    Optional<Object> getBestChallenge() throws DaoException;
}
