package net.ent.etrs.league.model.facade.impl;

import lombok.*;
import net.ent.etrs.league.model.dao.IDaoChallenge;
import net.ent.etrs.league.model.dao.exceptions.DaoException;
import net.ent.etrs.league.model.dao.impl.DaoFactory;
import net.ent.etrs.league.model.entities.Challenge;
import net.ent.etrs.league.model.facade.IFacadeMetierChallenge;
import net.ent.etrs.league.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FacadeMetierChallengeImpl implements IFacadeMetierChallenge {
    private IDaoChallenge daoChallenge;

    public FacadeMetierChallengeImpl() {
        daoChallenge = DaoFactory.getDaoChallenge();
    }

    /**
     * Permet de sauvegarder un challenge
     *
     * @param challenge le challenge à sauvegarder
     */
    @Override
    public void saveChallenge(Challenge challenge) throws BusinessException {
        try {
            daoChallenge.save(challenge);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Permet de supprimer un challenge
     *
     * @param challenge le challenge à supprimer
     */
    @Override
    public void deleteChallenge(Challenge challenge) throws BusinessException {
        try {
            daoChallenge.delete(challenge.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Permet de récupérer tous les challenges
     *
     * @return les challenges de la base
     */
    @Override
    public Set<Challenge> findAllChallenges() throws BusinessException {
        try {
            return Collections.unmodifiableSet(new HashSet<>(IterableUtils.toList(daoChallenge.findAll())));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Permet de récupérer le challenge qui rapport le plus de points
     *
     * @return le challenge qui possède le plus de points
     */
    @Override
    public Challenge getBestChallenge() {
        return daoChallenge.getBestChallenge().orElseThrow();
    }
}