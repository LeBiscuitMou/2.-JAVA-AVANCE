package model.entities;


import model.entities.references.Grade;
import model.entities.references.Ville;
import model.exceptions.GradeMinimumAgeException;
import model.exceptions.ValidException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

public final class EntitiesFactory {
    
    private EntitiesFactory() {
    }
    
    public static Club fabriquerClub(final String nom, final Ville ville) throws ValidException {
        return validate(new Club(nom, ville));
    }
    
    public static Competition fabriquerCompetition(final String nom, final Ville ville, final Integer annee) throws ValidException {
        return validate(new Competition(nom, ville, annee));
    }
    
    public static Judoka fabriquerJudoka(final String nom, final String prenom, final LocalDate dateNaissance, final int taille, final Grade grade, final int poids, final boolean isFeminin) throws GradeMinimumAgeException, ValidException {
        return validate(new Judoka(nom, prenom, dateNaissance, taille, grade, poids, isFeminin));
    }
    
    
    private static <T> T validate(T o) throws ValidException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        Set<ConstraintViolation<Object>> violations = validator.validate(o);
        if (violations.size() != 0) {
            throw new ValidException(violations);
        }
        return o;
    }
    
}
