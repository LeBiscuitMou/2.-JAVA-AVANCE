package net.ent.etrs.animalSoigneur.model.facade.impl;

import net.ent.etrs.animalSoigneur.model.dao.IDaoAnimal;
import net.ent.etrs.animalSoigneur.model.dao.exceptions.DaoException;
import net.ent.etrs.animalSoigneur.model.dao.impl.DaoFactory;
import net.ent.etrs.animalSoigneur.model.entities.Animal;
import net.ent.etrs.animalSoigneur.model.facade.IFacadeMetierAnimal;
import net.ent.etrs.animalSoigneur.model.facade.exceptions.BusinessException;
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
    public Animal creerAnimal(Animal pAnimal) throws BusinessException {
        try {
            return daoAnimal.save(pAnimal);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void supprimerAnimal(Animal pAnimal) throws BusinessException {
        try {
            daoAnimal.delete(pAnimal.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Animal modifierAnimal(Animal pAnimal) {
        return daoAnimal.modifierAnimal(pAnimal);
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
    public Optional<Animal> recupererAnimalById(Long pId) {
        return daoAnimal.recupererAnimalById(pId);
    }
}