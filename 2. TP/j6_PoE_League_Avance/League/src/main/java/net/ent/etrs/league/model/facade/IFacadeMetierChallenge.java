package net.ent.etrs.league.model.facade;

import net.ent.etrs.league.model.entities.Challenge;

import java.util.Set;

public interface IFacadeMetierChallenge {
    /**
     * Permet de sauvegarder un challenge
     *
     * @param challenge le challenge à sauvegarder
     */
    void saveChallenge(Challenge challenge);

    /**
     * Permet de supprimer un challenge
     *
     * @param challenge le challenge à supprimer
     */
    void deleteChallenge(Challenge challenge);

    /**
     * Permet de récupérer tous les challenges
     *
     * @return les challenges de la base
     */
    Set<Challenge> findAllChallenges();

    /**
     * Permet de récupérer le challenge qui rapport le plus de points
     *
     * @return le challenge qui possède le plus de points
     */
    Challenge getBestChallenge();
}
