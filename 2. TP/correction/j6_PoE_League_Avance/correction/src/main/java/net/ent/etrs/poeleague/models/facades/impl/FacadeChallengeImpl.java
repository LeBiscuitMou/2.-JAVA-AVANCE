package net.ent.etrs.poeleague.models.facades.impl;

import net.ent.etrs.poeleague.models.dao.DaoChallenge;
import net.ent.etrs.poeleague.models.dao.DaoFactory;
import net.ent.etrs.poeleague.models.dao.exceptions.DaoException;
import net.ent.etrs.poeleague.models.entities.Challenge;
import net.ent.etrs.poeleague.models.facades.FacadeChallenge;
import net.ent.etrs.poeleague.models.facades.exception.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.HashSet;
import java.util.Set;

public class FacadeChallengeImpl implements FacadeChallenge {

    DaoChallenge daoChallenge;

    public FacadeChallengeImpl() {
        this.daoChallenge = DaoFactory.getDaoChallenge();
    }

    /**
     * Permet de sauvegarder un challenge
     *
     * @param challenge le challenge à sauvegarder
     */
    @Override
    public void saveChallenge(Challenge challenge) throws BusinessException {
        try {
            this.daoChallenge.save(challenge);
        } catch (DaoException e) {
            throw new BusinessException("Impossible de sauvegarder le challenge", e);
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
            this.daoChallenge.save(challenge);
        } catch (DaoException e) {
            throw new BusinessException("Impossible de supprimer le challenge", e);
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
            return new HashSet<>(IterableUtils.toList(this.daoChallenge.findAll()));
        } catch (DaoException e) {
            throw new BusinessException("Impossible de récupérer les challenges", e);
        }
    }

    /**
     * Permet de récupérer le challenge qui rapport le plus de points
     *
     * @return le challenge qui possède le plus de points
     */
    @Override
    public Challenge findBestChallenge() throws BusinessException {
        try {
            return this.daoChallenge.getBestChallenge().orElseThrow();
        } catch (DaoException e) {
            throw new BusinessException("Impossible de récupérer le meilleur challenge", e);
        }
    }
}
