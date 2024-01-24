package net.ent.etrs.animalSoigneur.model.dao.impl;

import net.ent.etrs.animalSoigneur.model.dao.IDaoAnimal;
import net.ent.etrs.animalSoigneur.model.dao.base.JpaBaseDao;
import net.ent.etrs.animalSoigneur.model.entities.Animal;

import java.util.Optional;

public class DaoAnimalImpl extends JpaBaseDao<Animal> implements IDaoAnimal {
    @Override
    public Animal modifierAnimal(Animal pAnimal) {
        return null;
    }

    @Override
    public Optional<Animal> recupererAnimalById(Long pId) {
        return Optional.empty();
    }
}