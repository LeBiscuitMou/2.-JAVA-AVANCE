package net.ent.etrs.models.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.*;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {
    public static Personne fabriquerPersonne(String nom, String prenom, LocalDate dateNaissance) {
        Personne p = new Personne(nom, prenom, dateNaissance);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Personne>> s = validator.validate(p);

        if (!s.isEmpty()) {
            StringBuilder ret = new StringBuilder();
            for (ConstraintViolation<Personne> c : s) {
                ret.append(c.getMessage()).append("\n");
            }
            throw new ValidationException(ret.toString());
        }

        return p;
    }
}