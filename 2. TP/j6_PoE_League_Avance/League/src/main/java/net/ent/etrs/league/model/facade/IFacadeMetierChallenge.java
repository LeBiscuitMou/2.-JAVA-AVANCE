package net.ent.etrs.league.model.facade;

import net.ent.etrs.league.model.entities.Challenge;
import net.ent.etrs.league.model.facade.exceptions.BusinessException;

import java.util.Set;

public interface IFacadeMetierChallenge {
    /**
     * Permet de sauvegarder un challenge
     *
     * @param challenge le challenge à sauvegarder
     */
    void saveChallenge(Challenge challenge) throws BusinessException;

    /**
     * Permet de supprimer un challenge
     *
     * @param challenge le challenge à supprimer
     */
    void deleteChallenge(Challenge challenge) throws BusinessException;

    /**
     * Permet de récupérer tous les challenges
     *
     * @return les challenges de la base
     */
    Set<Challenge> findAllChallenges() throws BusinessException;

    /**
     * Permet de récupérer le challenge qui rapport le plus de points
     *
     * @return le challenge qui possède le plus de points
     */
    Challenge getBestChallenge();
}
