package net.ent.etrs.projet.models.commons;

import com.sun.xml.bind.v2.runtime.reflect.opt.Const;
import net.ent.etrs.projet.models.exceptions.EntitiesFactoryException;
import net.ent.etrs.projet.models.references.ConstMetier;

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
            throw new EntitiesFactoryException(ConstMetier.ERROR_CREATION + objet.getClass().getSimpleName() + "\n" + e.getMessage());
        }
    }

    public static <T> void refactorException(T objet, String otherError) throws EntitiesFactoryException {
        try {
            validate(objet);

            if(!otherError.isEmpty()){
                throw new EntitiesFactoryException(ConstMetier.ERROR_CREATION + objet.getClass().getSimpleName() + '\n' + otherError);
            }
        }catch (ValidationException e){
            throw new EntitiesFactoryException(ConstMetier.ERROR_CREATION + objet.getClass().getSimpleName() + "\n" + e.getMessage() + '\n' + otherError);
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
