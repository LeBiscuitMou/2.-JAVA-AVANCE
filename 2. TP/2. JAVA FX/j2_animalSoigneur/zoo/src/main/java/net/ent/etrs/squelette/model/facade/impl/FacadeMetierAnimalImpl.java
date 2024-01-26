package net.ent.etrs.squelette.model.facade.impl;

import net.ent.etrs.squelette.model.dao.IDaoAnimal;
import net.ent.etrs.squelette.model.dao.exceptions.DaoException;
import net.ent.etrs.squelette.model.dao.impl.DaoFactory;
import net.ent.etrs.squelette.model.entities.Animal;
import net.ent.etrs.squelette.model.facade.IFacadeMetierAnimal;
import net.ent.etrs.squelette.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FacadeMetierAnimalImpl implements IFacadeMetierAnimal {
    private IDaoAnimal daoAnimal;

    public FacadeMetierAnimalImpl() {
        daoAnimal = DaoFactory.getDaoAnimal();
    }

    @Override
    public Animal creerAnimal(Animal animal) throws BusinessException {
        try {
            return daoAnimal.save(animal);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void supprimerAnimal(Animal animal) throws BusinessException {
        try {
            daoAnimal.delete(animal.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Animal> recupererTousLesAnimals() throws BusinessException {
        try {
            return Collections.unmodifiableList(IterableUtils.toList(daoAnimal.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Animal> findById(Animal animal) throws BusinessException {
        try {
            return daoAnimal.find(animal.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}