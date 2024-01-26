package net.ent.etrs.zoo.models.dao;

import net.ent.etrs.zoo.models.dao.exceptions.DaoException;
import net.ent.etrs.zoo.models.entities.Animal;

public interface AnimalDao extends BaseDao<Animal> {
    Iterable<Animal> findAllWithoutSoigneur() throws DaoException;
}
