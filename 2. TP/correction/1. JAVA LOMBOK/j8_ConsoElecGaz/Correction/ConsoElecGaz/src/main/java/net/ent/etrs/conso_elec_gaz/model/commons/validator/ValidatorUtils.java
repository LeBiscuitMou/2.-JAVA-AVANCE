package net.ent.etrs.conso_elec_gaz.model.commons.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidatorUtils {
    public static <T> T validate(T o) throws ValidException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Object>> violations = validator.validate(o);
        if (violations.size() != 0) {
            throw new ValidException(violations);
        }
        return o;
    }
}
