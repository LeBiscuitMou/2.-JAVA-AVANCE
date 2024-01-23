package net.ent.etrs.heartStone.models.entities;

import javax.validation.*;
import java.util.Set;

public final class EntitiesValidation {
    private EntitiesValidation() {
    }

    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    public static <T> void validate(T entity) {
        Set<ConstraintViolation<T>> s = validator.validate(entity);
        if (!s.isEmpty()) {
            StringBuilder ret = new StringBuilder();
            for (ConstraintViolation<T> c : s) {
                ret.append("\n").append(c.getMessage());
            }
            throw new ValidationException(ret.toString());
        }
    }

}
