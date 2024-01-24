package net.ent.etrs.animalSoigneur.model.entities;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.animalSoigneur.model.commons.validator.ValidatorUtils;
import net.ent.etrs.animalSoigneur.model.entities.exceptions.EntitiesFactoryException;
import net.ent.etrs.animalSoigneur.model.entities.exceptions.SoigneurException;
import net.ent.etrs.animalSoigneur.model.entities.references.Type;

import javax.validation.ValidationException;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {
    public static Animal fabriquerAnimal(String nom, Integer poids, Float taille, Boolean dangerous, LocalDate dateNaissance, Type type) throws EntitiesFactoryException {
        Animal animal = new Animal();
        animal.setNom(nom);
        animal.setPoids(poids);
        animal.setTaille(taille);
        animal.setDangerous(dangerous);
        animal.setDateNaissance(dateNaissance);
        animal.setType(type);

        try {
            ValidatorUtils.refactorException(animal);
        } catch (ValidationException e){
            throw new EntitiesFactoryException(e.getMessage());
        }

        return animal;
    }

    public static Soigneur fabriquerSoigneur(String nom, String prenom, LocalDate dateNaissance, LocalDate dateArrivee, Animal animal, Type type) throws EntitiesFactoryException {
        Soigneur soigneur = new Soigneur();
        soigneur.setNom(nom);
        soigneur.setPrenom(prenom);
        soigneur.setDateNaissance(dateNaissance);
        soigneur.setDateArrivee(dateArrivee);

        try {
            soigneur.ajouterAnimal(animal);
            soigneur.ajouterType(type);

            ValidatorUtils.refactorException(soigneur);
        } catch (ValidationException | SoigneurException e){
            throw new EntitiesFactoryException(e.getMessage());
        }

        return soigneur;
    }
}
