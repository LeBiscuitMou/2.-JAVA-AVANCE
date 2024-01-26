package net.ent.etrs.projetjavamaier.models.daos.base;




import net.ent.etrs.projetjavamaier.models.daos.exception.DaoException;
import net.ent.etrs.projetjavamaier.models.entities.AbstractEntity;

import java.util.Optional;

public interface BaseDao<T extends AbstractEntity> {

    T save(T entity) throws DaoException;

    Optional<T> find(Long id) throws DaoException;

    Iterable<T> findAll() throws DaoException;

    void delete(Long id) throws DaoException;

    boolean exists(Long id) throws DaoException;

    long count() throws DaoException;

    // void delete(T entity);
}