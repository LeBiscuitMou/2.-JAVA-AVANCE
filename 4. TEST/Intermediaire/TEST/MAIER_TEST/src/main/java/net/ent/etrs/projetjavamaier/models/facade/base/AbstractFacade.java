package net.ent.etrs.projetjavamaier.models.facade.base;

import lombok.Setter;
import net.ent.etrs.projetjavamaier.models.daos.base.BaseDao;
import net.ent.etrs.projetjavamaier.models.daos.exception.DaoException;
import net.ent.etrs.projetjavamaier.models.entities.AbstractEntity;
import net.ent.etrs.projetjavamaier.models.facade.exceptions.BusinessException;

import java.util.Optional;

public abstract class AbstractFacade<T extends AbstractEntity> implements FacadeMetier<T> {

    @Setter
    private BaseDao<T> dao;
    @Override
    public T save(T entity) throws BusinessException {
        try {
            return this.dao.save(entity);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Optional<T> find(Long id) throws BusinessException {
        try {
            return this.dao.find(id);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Iterable<T> findAll() throws BusinessException {
        try {
            return this.dao.findAll();
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void delete(Long id) throws BusinessException {
        try {
            this.dao.delete(id);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

   /* @Override
    public void delete(T entity) throws BusinessException {
        try {
            this.dao.delete(entity);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }*/

    @Override
    public boolean exists(Long id) throws BusinessException {
        try {
            return this.dao.exists(id);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public long count() throws BusinessException {
        try {
            return this.dao.count();
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

}