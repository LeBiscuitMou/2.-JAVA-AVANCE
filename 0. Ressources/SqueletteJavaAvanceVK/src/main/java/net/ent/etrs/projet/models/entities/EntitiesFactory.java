package net.ent.etrs.projet.models.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.projet.models.exceptions.EntitiesFactoryException;
import net.ent.etrs.projet.models.commons.ValidatorUtils;
import net.ent.etrs.projet.models.references.ConstMetier;

import javax.validation.ValidationException;
import java.time.LocalDate;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {
    public static Exemple fabriquerExemple() throws EntitiesFactoryException {
        Exemple exemple = new Exemple();

        try {
            ValidatorUtils.refactorException(exemple);
        }catch (ValidationException e){
            throw new EntitiesFactoryException(ConstMetier.ERROR_CREATION + Exemple.class.getSimpleName(),e);
        }

        return exemple;
    }






}



