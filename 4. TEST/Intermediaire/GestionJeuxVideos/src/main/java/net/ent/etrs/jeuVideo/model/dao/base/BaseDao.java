package net.ent.etrs.jeuVideo.model.dao.base;

import net.ent.etrs.jeuVideo.model.dao.exceptions.DaoException;
import net.ent.etrs.jeuVideo.model.entities.AbstractEntity;

import java.util.Optional;

public interface BaseDao<T> {

    T save(T entity) throws DaoException;

    Optional<T> find(Long id) throws DaoException;

    Iterable<T> findAll() throws DaoException;

    void delete(T id) throws DaoException;

    boolean exists(Long id) throws DaoException;

    Long count() throws DaoException;

}
