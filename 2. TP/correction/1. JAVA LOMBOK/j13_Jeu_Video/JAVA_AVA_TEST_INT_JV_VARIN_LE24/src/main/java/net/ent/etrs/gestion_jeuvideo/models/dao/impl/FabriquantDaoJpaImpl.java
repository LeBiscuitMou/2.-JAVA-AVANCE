package net.ent.etrs.gestion_jeuvideo.models.dao.impl;

import net.ent.etrs.gestion_jeuvideo.models.dao.IDaoFabriquant;
import net.ent.etrs.gestion_jeuvideo.models.dao.exceptions.DaoException;
import net.ent.etrs.gestion_jeuvideo.models.entities.Fabriquant;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class FabriquantDaoJpaImpl extends JpaBaseDao<Fabriquant> implements IDaoFabriquant {

    @Override
    public Optional<Fabriquant> findFabriquantByIdentity(String nomFabriquant) throws DaoException {
        try {
            TypedQuery<Fabriquant> query = this.em.createQuery("""
                            SELECT t
                            FROM Fabriquant t
                            WHERE t.nom = :nomFabriquant"""
                    , Fabriquant.class);
            query.setParameter("nomFabriquant", nomFabriquant);
            
            
            query.setMaxResults(1);
            return Optional.ofNullable(query.getSingleResult()); 

        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}