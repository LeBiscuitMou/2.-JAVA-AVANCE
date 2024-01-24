package net.ent.etrs.animalSoigneur.model.dao;

import net.ent.etrs.animalSoigneur.model.dao.base.BaseDao;
import net.ent.etrs.animalSoigneur.model.entities.Animal;

import java.util.Optional;

public interface IDaoAnimal extends BaseDao<Animal> {

    Animal modifierAnimal(Animal pAnimal);

    Optional<Animal> recupererAnimalById(Long pId);
}