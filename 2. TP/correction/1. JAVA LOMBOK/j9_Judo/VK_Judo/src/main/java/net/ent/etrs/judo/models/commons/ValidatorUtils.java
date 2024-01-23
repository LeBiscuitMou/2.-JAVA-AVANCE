package net.ent.etrs.judo.models.commons;

import net.ent.etrs.judo.models.exceptions.EntitiesFactoryException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

public final class ValidatorUtils {
    private ValidatorUtils() {    }

    public static <T> T refactorException(T objet) throws EntitiesFactoryException {
        try {
            return validate(objet);
        }catch (ValidationException e){
            throw new EntitiesFactoryException("\nune erreur est survenue dans la création de  " + objet.getClass().getSimpleName() + "\n" + e.getMessage());
        }
    }

    public static <T> T validate(T objetFabriquer) throws ValidationException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> s = validator.validate(objetFabriquer);
        if(!s.isEmpty()){
            throw new ValidationException(s.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n")));
        }
        return objetFabriquer;
    }
}
