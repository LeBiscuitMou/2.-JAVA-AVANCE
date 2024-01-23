package net.ent.etrs.league.model.entities;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.league.model.commons.validator.ValidatorUtils;
import net.ent.etrs.league.model.entities.exceptions.EntitiesFactoryException;
import net.ent.etrs.league.model.entities.references.ConstantesMetier;
import net.ent.etrs.league.model.entities.references.LabySpecialite;

import javax.validation.ValidationException;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {
    public static Personnage fabriquerPersonnage(String pseudo, Integer level, LabySpecialite build) throws EntitiesFactoryException {
        Personnage personnage = new Personnage();

        personnage.setPseudo(pseudo);
        personnage.setLevel(level);
        personnage.setBuild(build);

        try {
            ValidatorUtils.refactorException(personnage);
        }catch (ValidationException e){
            throw new EntitiesFactoryException(e.getMessage(), e);
        }

        return personnage;
    }

    public static League fabriquerLeague(String nom, LocalDate dateDebut, LocalDate dateFin) throws EntitiesFactoryException {
        League league = new League();

        league.setNom(nom);
        league.setDateDebut(dateDebut);
        league.setDateFin(dateFin);

        try {
            ValidatorUtils.refactorException(league);
        }catch (ValidationException e){
            throw new EntitiesFactoryException(e.getMessage(), e);
        }

        return league;
    }

    public static Challenge fabriquerChallenge(String nom, String desc, Integer rewardPoints) throws EntitiesFactoryException {
        Challenge challenge = new Challenge();

        challenge.setNom(nom);
        challenge.setDesc(desc);
        challenge.setRewardPoints(rewardPoints);

        try {
            ValidatorUtils.refactorException(challenge);
        }catch (ValidationException e){
            throw new EntitiesFactoryException(e.getMessage());
        }

        return challenge;
    }
}
