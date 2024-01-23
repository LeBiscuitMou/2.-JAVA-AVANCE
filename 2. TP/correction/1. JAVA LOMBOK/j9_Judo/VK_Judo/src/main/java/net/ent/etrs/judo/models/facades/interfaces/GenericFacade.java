package net.ent.etrs.judo.models.facades.interfaces;

import net.ent.etrs.judo.models.entities.AbstractEntity;
import net.ent.etrs.judo.models.exceptions.BusinessException;

import java.util.Optional;

// Interface de façade générique

public interface GenericFacade<T extends AbstractEntity> {
    T save(T entity) throws BusinessException;

    void delete(Long id) throws BusinessException;

    Iterable<T> findAll() throws BusinessException;

    Optional<T> find(Long id) throws BusinessException;
}