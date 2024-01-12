package net.ent.etrs.judo.models.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.judo.models.entities.references.Categorie;
import net.ent.etrs.judo.models.entities.references.Grade;
import net.ent.etrs.judo.models.entities.references.Ville;
import net.ent.etrs.judo.models.exceptions.EntitiesFactoryJudokaException;
import net.ent.etrs.judo.models.commons.ValidatorUtils;
import javax.validation.ValidationException;
import java.time.LocalDate;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {


    public static Club fabriquerClub(String nom, Ville ville) throws EntitiesFactoryJudokaException {
        Club club = new Club();
        club.setNom(nom);
        club.setVille(ville);

        try {
            ValidatorUtils.refactorException(club);
        }catch (ValidationException e){
            throw new EntitiesFactoryJudokaException("une érreur est survenue lors de la création de l'club" ,e);
        }

        return club;
    }

    public static Competition fabriquerCompetition(String nom, Integer annee, Ville ville) throws EntitiesFactoryJudokaException {
        Competition competition = new Competition();
        competition.setNom(nom);
        competition.setVille(ville);
        competition.setAnnee(annee);

        try {
            ValidatorUtils.refactorException(competition);
        }catch (ValidationException e){
            throw new EntitiesFactoryJudokaException("une érreur est survenue lors de la création de l'competition" ,e);
        }

        return competition;
    }

    public static Judoka fabriquerJudoka(String nom, String prenom, LocalDate dateDeNaissance, Integer taille, Grade grade, Integer poids, boolean isFeminin) throws EntitiesFactoryJudokaException {
        Judoka judoka = new Judoka();
        judoka.setNom(nom);
        judoka.setPrenom(prenom);
        judoka.setDateDeNaissance(dateDeNaissance);
        judoka.setTaille(taille);
        judoka.setGrade(grade);
        judoka.setCategorie(Categorie.get(isFeminin, poids));

        try {
            ValidatorUtils.refactorException(judoka);
        }catch (ValidationException e){
            throw new EntitiesFactoryJudokaException("une érreur est survenue lors de la création de l'judoka" ,e);
        }

        return judoka;
    }






}



