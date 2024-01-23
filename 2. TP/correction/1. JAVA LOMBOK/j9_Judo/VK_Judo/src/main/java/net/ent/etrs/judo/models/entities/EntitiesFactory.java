package net.ent.etrs.judo.models.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.judo.models.commons.ValidatorUtils;
import net.ent.etrs.judo.models.entities.references.Categorie;
import net.ent.etrs.judo.models.entities.references.Grade;
import net.ent.etrs.judo.models.entities.references.Ville;
import net.ent.etrs.judo.models.exceptions.ClubException;
import net.ent.etrs.judo.models.exceptions.EntitiesFactoryException;
import net.ent.etrs.judo.models.exceptions.GradeMinimumAgeException;
import net.ent.etrs.judo.models.exceptions.JudokaException;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.Set;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {


    public static Club fabriquerClub(String nom, Ville ville, Set<Judoka> judokaSet) throws EntitiesFactoryException {
        Club club = ValidatorUtils.refactorException(new Club(nom, ville));

        for(Judoka judoka : judokaSet){
            try {
                club.ajouterJudoka(judoka);
            } catch (ClubException e) {
                throw new EntitiesFactoryException(e);
            }
        }
        return club;
    }

    public static Competition fabriquerCompetition(String nom, Integer annee, Ville ville) throws EntitiesFactoryException {
        return ValidatorUtils.refactorException(new Competition(nom, annee, ville));
    }

    public static Judoka fabriquerJudoka(String nom, String prenom, LocalDate dateDeNaissance, Integer taille, Grade grade, Integer poids, boolean isFeminin) throws GradeMinimumAgeException, EntitiesFactoryException {
        return ValidatorUtils.refactorException(new Judoka(nom, prenom, dateDeNaissance, taille, Categorie.get(isFeminin, poids), grade));
    }


}



