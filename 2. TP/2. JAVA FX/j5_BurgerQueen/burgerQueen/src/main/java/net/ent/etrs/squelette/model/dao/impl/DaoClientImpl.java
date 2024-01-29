package net.ent.etrs.squelette.model.dao.impl;

import net.ent.etrs.squelette.model.dao.IDaoClient;
import net.ent.etrs.squelette.model.dao.base.JpaBaseDao;
import net.ent.etrs.squelette.model.dao.exceptions.DaoException;
import net.ent.etrs.squelette.model.entities.Client;

import javax.persistence.TypedQuery;

public class DaoClientImpl extends JpaBaseDao<Client> implements IDaoClient {
    @Override
    public Client searchClientByNomPrenom(String nom, String prenom) throws DaoException {
        try {
            TypedQuery<Client> query = this.em.createQuery("""
                            SELECT t
                            FROM Client t
                            WHERE t.nom = :nom
                            AND t.prenom = :prenom"""
                    , Client.class);

            query.setParameter("nom", nom);
            query.setParameter("prenom", prenom);

            return query.getSingleResult();
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }
}