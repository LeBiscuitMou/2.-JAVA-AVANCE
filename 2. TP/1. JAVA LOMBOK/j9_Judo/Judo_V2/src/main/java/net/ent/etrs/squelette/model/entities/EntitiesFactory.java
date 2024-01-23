package net.ent.etrs.squelette.model.entities;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.squelette.model.commons.validator.ValidException;
import net.ent.etrs.squelette.model.commons.validator.ValidatorUtils;
import net.ent.etrs.squelette.model.entities.exceptions.EntitiesFactoryException;
import net.ent.etrs.squelette.model.entities.references.ConstantesMetier;
import net.ent.etrs.squelette.model.entities.references.Grade;
import net.ent.etrs.squelette.model.entities.references.Ville;

import javax.validation.ValidationException;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {
    public static Competition fabriquerCompetition(String nom, Integer annee, Ville ville) throws EntitiesFactoryException {
        Competition competition = new Competition();
        competition.setNom(nom);
        competition.setAnnee(annee);
        competition.setVille(ville);

        try {
            ValidatorUtils.refactorException(competition);
        }catch (ValidationException e){
            throw new EntitiesFactoryException(ConstantesMetier.ERROR_CREATION + Competition.class.getSimpleName(), e);
        }

        return competition;
    }

    public static Judoka fabriquerJudoka(String nom, String prenom, LocalDate dateNaissance, Integer taille, Grade grade, Integer poids, boolean pFeministe) throws EntitiesFactoryException {
        Judoka judoka = new Judoka();
        judoka.setNom(nom);
        judoka.setPrenom(prenom);
        judoka.setDateNaissance(dateNaissance);
        judoka.setTaille(taille);
        judoka.setGrade(grade);

        try {
            ValidatorUtils.refactorException(judoka);
        }catch (ValidationException e){
            throw new EntitiesFactoryException(e.getMessage());
        }

        return judoka;
    }
}
