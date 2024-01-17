package net.ent.etrs.squelette.model.entities;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.squelette.model.commons.validator.ValidException;
import net.ent.etrs.squelette.model.commons.validator.ValidatorUtils;
import net.ent.etrs.squelette.model.entities.exceptions.EntitiesFactoryException;
import net.ent.etrs.squelette.model.entities.references.ConstantesMetier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {
    public static Exemple fabriquerExemple() throws EntitiesFactoryException {
        Exemple exemple = new Exemple();

        try {
            ValidatorUtils.refactorException(exemple);
        } catch (EntitiesFactoryException e) {
            throw new EntitiesFactoryException(ConstantesMetier.ERROR_CREATION + Exemple.class.getSimpleName(), e);
        }

        return exemple;
    }
}
