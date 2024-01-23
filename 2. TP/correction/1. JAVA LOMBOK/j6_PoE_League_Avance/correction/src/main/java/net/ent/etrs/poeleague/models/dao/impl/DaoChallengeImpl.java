package net.ent.etrs.poeleague.models.dao.impl;

import net.ent.etrs.poeleague.models.dao.DaoChallenge;
import net.ent.etrs.poeleague.models.dao.base.JpaBaseDao;
import net.ent.etrs.poeleague.models.dao.exceptions.DaoException;
import net.ent.etrs.poeleague.models.entities.Challenge;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class DaoChallengeImpl extends JpaBaseDao<Challenge> implements DaoChallenge {
    @Override
    public Optional<Challenge> getBestChallenge() throws DaoException {
        try {
            TypedQuery<Challenge> tp = this.em.createQuery(
                    "SELECT t FROM Challenge t " +
                            "WHERE t.rewardPoints = ( " +
                            "SELECT MAX(c1.rewardPoints) FROM Challenge c1)", Challenge.class);
            return Optional.ofNullable(tp.getSingleResult());
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
