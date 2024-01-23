package net.ent.etrs.logistock.model.dao.impl;

import lombok.*;
import net.ent.etrs.logistock.model.dao.IDaoClient;
import net.ent.etrs.logistock.model.dao.base.AbstractJpaDao;
import net.ent.etrs.logistock.model.dao.base.BaseDao;
import net.ent.etrs.logistock.model.entities.Client;
import net.ent.etrs.logistock.model.exceptions.DaoException;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Optional;

public class DaoClientImpl extends AbstractJpaDao<Client, Long> implements IDaoClient {
    @Override
    public Optional<Client> getClientById(Long pId) throws DaoException {
        try {
            TypedQuery<Client> tp = this.em.createQuery(
                    "SELECT c FROM Client c " +
                            "WHERE c.id = :id",
                    Client.class
            );
            tp.setParameter("id", pId);
            return Optional.ofNullable(tp.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (PersistenceException | IllegalArgumentException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}