package net.ent.etrs.league.model.dao.impl;

import lombok.*;
import net.ent.etrs.league.model.dao.IDaoChallenge;
import net.ent.etrs.league.model.dao.base.JpaBaseDao;
import net.ent.etrs.league.model.dao.exceptions.DaoException;
import net.ent.etrs.league.model.entities.Challenge;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Optional;

public class DaoChallengeImpl extends JpaBaseDao<Challenge> implements IDaoChallenge {
    @Override
    public Optional<Object> getBestChallenge() throws DaoException {
        try {
            TypedQuery<Challenge> query = this.em.createQuery("""
                            SELECT t
                            FROM Challenge t
                            WHERE t.rewardPoints = (SELECT MAX(c1.rewardPoints)
                                                    FROM Challenge c1)"""
                    , Challenge.class);
            query.setMaxResults(1);
            return Optional.ofNullable(query.getSingleResult());
        } catch (IllegalArgumentException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}