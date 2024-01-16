package net.ent.etrs.squelette.model.entities;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.squelette.model.commons.validator.ValidException;
import net.ent.etrs.squelette.model.commons.validator.ValidatorUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {
    public static Exemple fabriquerExemple(String nom) throws ValidException {
        return ValidatorUtils.validate(new Exemple(nom));
    }
}
