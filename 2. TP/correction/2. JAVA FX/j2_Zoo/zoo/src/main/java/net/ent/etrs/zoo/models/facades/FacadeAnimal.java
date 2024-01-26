package net.ent.etrs.zoo.models.facades;

import net.ent.etrs.zoo.models.dao.AnimalDao;
import net.ent.etrs.zoo.models.dao.SoigneurDao;
import net.ent.etrs.zoo.models.dao.impl.AnimalDaoImpl;
import net.ent.etrs.zoo.models.dao.impl.SoigneurDaoImpl;
import net.ent.etrs.zoo.models.entities.Animal;
import net.ent.etrs.zoo.models.entities.Soigneur;
import net.ent.etrs.zoo.models.facades.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.HashSet;
import java.util.Set;

public class FacadeAnimal {

    private AnimalDao dao;

    public FacadeAnimal() {
        this.dao = new AnimalDaoImpl();
    }

    public void save(Animal animal) throws BusinessException {
        try {
            this.dao.save(animal);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    public void delete(Animal animal) throws BusinessException {
        try {
            this.dao.delete(animal.getId());
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    public Set<Animal> findAll() throws BusinessException {
        try {
            return new HashSet<>(IterableUtils.toList(this.dao.findAll()));
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    public Set<Animal> findAllWithoutSoigneur() throws BusinessException {
        try {
            return new HashSet<>(IterableUtils.toList(this.dao.findAllWithoutSoigneur()));
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
}
