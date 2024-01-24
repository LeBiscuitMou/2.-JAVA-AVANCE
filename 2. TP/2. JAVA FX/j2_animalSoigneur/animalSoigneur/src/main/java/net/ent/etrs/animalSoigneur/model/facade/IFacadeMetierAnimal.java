package net.ent.etrs.animalSoigneur.model.facade;

import net.ent.etrs.animalSoigneur.model.entities.Animal;
import net.ent.etrs.animalSoigneur.model.facade.exceptions.BusinessException;

import java.util.List;
import java.util.Optional;

public interface IFacadeMetierAnimal {
    Animal creerAnimal(Animal pAnimal) throws BusinessException;

    void supprimerAnimal(Animal pAnimal) throws BusinessException;

    Animal modifierAnimal(Animal pAnimal);

    List<Animal> recupererTousLesAnimals() throws BusinessException;

    Optional<Animal> recupererAnimalById(Long pId);
}