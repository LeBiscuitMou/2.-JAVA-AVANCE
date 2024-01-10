package net.ent.etrs.projet.models.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.projet.models.exceptions.EntitiesFactoryException;
import net.ent.etrs.projet.models.commons.ValidatorUtils;
import javax.validation.ValidationException;
import java.time.LocalDate;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {


    public static Exemple fabriquerExemple(String nom, LocalDate dateNaissance, int entier) throws EntitiesFactoryException {
        Exemple exemple = new Exemple();
        exemple.setNom(nom);
        exemple.setEntier(entier);
        exemple.setDateNaissance(dateNaissance);

        try {
            ValidatorUtils.refactorException(exemple);
        }catch (ValidationException e){
            throw new EntitiesFactoryException("une érreur est survenue lors de la création de l'exemple" ,e);
        }

        return exemple;
    }






}



