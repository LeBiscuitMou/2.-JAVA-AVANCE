package net.ent.etrs.judo.models.commons;

import net.ent.etrs.judo.models.exceptions.EntitiesFactoryJudokaException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

public final class ValidatorUtils {
    private ValidatorUtils() {    }

    public static <T> void refactorException(T objet) throws EntitiesFactoryJudokaException {
        try {
            validate(objet);
        }catch (ValidationException e){
            throw new EntitiesFactoryJudokaException("\nune erreur est survenue dans la création de  " + objet.getClass().getSimpleName() + "\n" + e.getMessage());
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
