package net.ent.etrs.heartStone.models.dao;



import net.ent.etrs.heartStone.models.dao.exceptions.DaoException;
import net.ent.etrs.heartStone.models.entities.AbstractEntity;

import java.sql.SQLException;
import java.util.Optional;

public interface BaseDao<T extends AbstractEntity> {

    T save(T entity) throws DaoException, SQLException;

    Optional<T> find(Long id) throws DaoException;

    Iterable<T> findAll() throws DaoException, SQLException;

    void delete(Long id) throws DaoException;

    boolean exists(Long id) throws DaoException;

    long count() throws DaoException;

}
