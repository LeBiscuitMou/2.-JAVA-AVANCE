package net.ent.etrs.hockey.models.dao.base;


import net.ent.etrs.hockey.models.dao.exceptions.DaoException;

import java.util.Optional;

public interface BaseDao<T> {

    T save(T entity) throws DaoException;

    Optional<T> find(Long id) throws DaoException;

    Iterable<T> findAll() throws DaoException;

    void delete(T entity) throws DaoException;

    Long count() throws DaoException;

    boolean exists(Long id) throws DaoException;
}
