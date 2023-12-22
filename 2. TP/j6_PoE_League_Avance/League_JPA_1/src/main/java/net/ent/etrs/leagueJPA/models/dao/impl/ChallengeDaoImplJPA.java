package net.ent.etrs.leagueJPA.models.dao.impl;

import net.ent.etrs.leagueJPA.models.dao.ChallengeDao;
import net.ent.etrs.leagueJPA.models.dao.exceptions.DaoException;
import net.ent.etrs.leagueJPA.models.entities.Challenge;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class ChallengeDaoImplJPA extends JpaBaseDao<Challenge> implements ChallengeDao {
    EntityManager entityManager;
    @Override
    public Optional<Challenge> getBestChallenge() throws DaoException {
        TypedQuery<Challenge> query = entityManager.createQuery("SELECT c " +
                                                                            "FROM Challenge c " +
                                                                            "WHERE c.rewardPoints = (SELECT MAX(c.rewardPoints) " +
                                                                                                    "FROM Challenge c)", Challenge.class);
        return Optional.ofNullable(query.getSingleResult());
    }
}