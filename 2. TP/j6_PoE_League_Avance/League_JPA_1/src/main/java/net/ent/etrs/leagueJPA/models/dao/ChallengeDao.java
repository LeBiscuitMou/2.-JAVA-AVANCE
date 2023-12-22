package net.ent.etrs.leagueJPA.models.dao;

import net.ent.etrs.leagueJPA.models.dao.exceptions.DaoException;
import net.ent.etrs.leagueJPA.models.entities.Challenge;

import java.util.Optional;

public interface ChallengeDao extends BaseDao<Challenge>  {
    Optional<Challenge> getBestChallenge() throws DaoException;
}
