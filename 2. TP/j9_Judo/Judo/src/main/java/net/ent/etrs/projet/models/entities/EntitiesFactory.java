package net.ent.etrs.projet.models.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.projet.models.exceptions.EntitiesFactoryException;
import net.ent.etrs.projet.models.commons.ValidatorUtils;
import net.ent.etrs.projet.models.references.Grade;
import net.ent.etrs.projet.models.references.Ville;

import javax.validation.ValidationException;
import java.time.LocalDate;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {
    public static Club fabriquerClub(String nom, Ville ville) throws EntitiesFactoryException {
        Club club = new Club(ville, nom);

        try {
            ValidatorUtils.refactorException(club);
        }catch (ValidationException e){
            throw new EntitiesFactoryException("une erreur est survenue lors de la création de l'exemple" ,e);
        }
        return club;
    }

//    public static Judoka fabriquerJudoka(String nom, String prenom, LocalDate dateNaissance, Integer classement, Grade grade, Integer taille, boolean exemple) throws EntitiesFactoryException {
//        Judoka judoka = new Judoka(dateNaissance, grade, classement);
//
//        try {
//            ValidatorUtils.refactorException(judoka);
//        }catch (ValidationException e){
//            throw new EntitiesFactoryException("une erreur est survenue lors de la création de l'exemple" ,e);
//        }
//        return judoka;
//    }






}



