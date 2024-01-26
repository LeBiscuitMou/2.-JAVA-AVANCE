package net.ent.etrs.squelette.model.facade;

import net.ent.etrs.squelette.model.entities.Animal;
import net.ent.etrs.squelette.model.facade.exceptions.BusinessException;

import java.util.List;
import java.util.Optional;

public interface IFacadeMetierAnimal {
    Animal creerAnimal(Animal animal) throws BusinessException;

    void supprimerAnimal(Animal animal) throws BusinessException;

    List<Animal> recupererTousLesAnimals() throws BusinessException;

    Optional<Animal> findById(Animal animal) throws BusinessException;
}