package net.ent.etrs.poeleague.models.dao;

import net.ent.etrs.poeleague.models.dao.base.BaseDao;
import net.ent.etrs.poeleague.models.dao.exceptions.DaoException;
import net.ent.etrs.poeleague.models.entities.Challenge;

import java.util.Optional;

public interface DaoChallenge extends BaseDao<Challenge> {
    Optional<Challenge> getBestChallenge() throws DaoException;
}
