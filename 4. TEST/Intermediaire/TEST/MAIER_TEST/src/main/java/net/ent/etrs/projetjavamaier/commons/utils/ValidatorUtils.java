package net.ent.etrs.projetjavamaier.commons.utils;


import net.ent.etrs.projetjavamaier.models.entities.exceptions.EntitiesFactoryException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

public final class ValidatorUtils {
    private ValidatorUtils() {    }

    public static <T> void refactorException(T objet) throws EntitiesFactoryException {
        try {
            validate(objet);
        }catch (ValidationException e){
            throw new EntitiesFactoryException("\nune erreur est survenue dans la création de  " + objet.getClass().getSimpleName() + "\n" + e.getMessage());
        }
    }

    public static <T> void validate(T objetFabriquer) throws ValidationException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> s = validator.validate(objetFabriquer);
        if(!s.isEmpty()){
            throw new ValidationException(s.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n")));
        }
    }
}
