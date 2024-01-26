package net.ent.etrs.projetjavamaier.models.facade.base;

import net.ent.etrs.projetjavamaier.models.entities.AbstractEntity;
import net.ent.etrs.projetjavamaier.models.facade.exceptions.BusinessException;

import java.util.Optional;

public interface FacadeMetier<T extends AbstractEntity> {

    T save(T entity) throws BusinessException;

    Optional<T> find(Long id) throws BusinessException;

    Iterable<T> findAll() throws BusinessException;

    void delete(Long id) throws BusinessException;

    //void delete(T entity) throws BusinessException;

    boolean exists(Long id) throws BusinessException;

    long count() throws BusinessException;

}