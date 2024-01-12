package net.ent.etrs.judo.models.facades.impl;

import net.ent.etrs.judo.models.dao.BaseDao;
import net.ent.etrs.judo.models.dao.exceptions.DaoException;
import net.ent.etrs.judo.models.entities.AbstractEntity;
import net.ent.etrs.judo.models.exceptions.BusinessException;
import net.ent.etrs.judo.models.facades.interfaces.GenericFacade;
import net.ent.etrs.judo.models.references.ConstMetier;

import java.util.Optional;

public abstract class GenericFacadeImpl<T extends AbstractEntity> implements GenericFacade<T> {
    private final BaseDao<T> entityDao;
    private final String className;

    protected GenericFacadeImpl(BaseDao<T> entityDao) {
        this.entityDao = entityDao;
        this.className = entityDao.getClass().getSimpleName();
    }

    @Override
    public T save(T entity) throws BusinessException {
        try {
            if(null == entity){
                throw new BusinessException(className + ConstMetier.ERROR_NULL);
            }
            return entityDao.save(entity);
        } catch (DaoException e) {
            throw new BusinessException(ConstMetier.ERROR_SAVE + className, e);
        }
    }

    @Override
    public void delete(Long id) throws BusinessException {
        try {
            if(null == id){
                throw new BusinessException("l'id de " + className + ConstMetier.ERROR_NULL);
            }
            entityDao.delete(id);
        } catch (DaoException e) {
            throw new BusinessException(ConstMetier.ERROR_DELETE + className, e);
        }
    }

    @Override
    public Iterable<T> findAll() throws BusinessException {
        try {
            return entityDao.findAll();
        } catch (DaoException e) {
            throw new BusinessException(ConstMetier.ERROR_FIND + className, e);
        }
    }

    @Override
    public Optional<T> find(Long id) throws BusinessException {
        try {
            if(null == id){
                throw new BusinessException("l'id de " + className + ConstMetier.ERROR_NULL);
            }
            return entityDao.find(id);
        } catch (DaoException e) {
            throw new BusinessException(ConstMetier.ERROR_FIND + className, e);
        }
    }
}
