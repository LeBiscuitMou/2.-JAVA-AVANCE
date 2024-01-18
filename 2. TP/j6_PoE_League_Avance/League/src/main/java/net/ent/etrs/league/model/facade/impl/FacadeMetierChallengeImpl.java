package net.ent.etrs.league.model.facade.impl;

import lombok.*;
import net.ent.etrs.league.model.entities.Challenge;
import net.ent.etrs.league.model.facade.IFacadeMetierChallenge;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

public class FacadeMetierChallengeImpl implements IFacadeMetierChallenge {
    /**
     * Permet de sauvegarder un challenge
     *
     * @param challenge le challenge à sauvegarder
     */
    @Override
    public void saveChallenge(Challenge challenge) {

    }

    /**
     * Permet de supprimer un challenge
     *
     * @param challenge le challenge à supprimer
     */
    @Override
    public void deleteChallenge(Challenge challenge) {

    }

    /**
     * Permet de récupérer tous les challenges
     *
     * @return les challenges de la base
     */
    @Override
    public Set<Challenge> findAllChallenges() {
        return null;
    }

    /**
     * Permet de récupérer le challenge qui rapport le plus de points
     *
     * @return le challenge qui possède le plus de points
     */
    @Override
    public Challenge getBestChallenge() {
        return null;
    }
}