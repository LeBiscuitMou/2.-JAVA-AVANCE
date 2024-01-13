package net.ent.etrs.squelette.model.dao.base;

import net.ent.etrs.squelette.model.exceptions.DaoException;

import java.io.Serializable;
import java.util.Optional;

public interface BaseDao<T, ID extends Serializable> {

    T save(T entity) throws DaoException;

    Optional<T> find(ID id) throws DaoException;

    Iterable<T> findAll() throws DaoException;

    void delete(ID id) throws DaoException;

    boolean exists(ID id) throws DaoException;

    long count() throws DaoException;

}
