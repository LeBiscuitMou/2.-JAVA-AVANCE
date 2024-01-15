package net.ent.etrs.logistock.model.dao.impl;

import net.ent.etrs.logistock.model.dao.IDaoClient;
import net.ent.etrs.logistock.model.dao.base.AbstractJpaDao;
import net.ent.etrs.logistock.model.dao.base.BaseDao;
import net.ent.etrs.logistock.model.entities.Article;
import net.ent.etrs.logistock.model.entities.Client;
import net.ent.etrs.logistock.model.entities.Commande;
import net.ent.etrs.logistock.model.exceptions.DaoException;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Optional;

public class DaoClientImpl extends AbstractJpaDao<Client, Long> implements IDaoClient {
    @Override
    public Optional<Client> findClientById(Long pId) throws DaoException {
        try {
            TypedQuery<Client> tp = this.em.createQuery(
                    "SELECT c FROM Client c " +
                            "WHERE c.id = :id", Client.class);
            tp.setParameter("id", pId);
            return Optional.ofNullable(tp.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}