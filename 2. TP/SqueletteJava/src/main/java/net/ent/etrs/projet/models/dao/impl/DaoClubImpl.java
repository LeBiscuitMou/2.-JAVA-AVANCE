package net.ent.etrs.projet.models.dao.impl;

import net.ent.etrs.projet.models.dao.DaoClub;
import net.ent.etrs.projet.models.dao.exceptions.DaoException;
import net.ent.etrs.projet.models.entities.Club;
import net.ent.etrs.projet.models.references.Ville;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class DaoClubImpl extends AbstractJpaDao<Club, Serializable> implements DaoClub {

    @Override
    public Optional<Club> getClub(Long pId) throws DaoException {
        try {
            TypedQuery<Club> tp = this.em.createQuery(
                    "SELECT c FROM Club c " +
                            "WHERE c.id = :id", Club.class);
            tp.setParameter("id", pId);

            return Optional.ofNullable(tp.getSingleResult());
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public List<Club> findAllClubByVille(Ville pVille) throws DaoException {
        try {
            TypedQuery<Club> tp = this.em.createQuery(
                    "SELECT c FROM Club c " +
                            "WHERE c.ville = :ville", Club.class);
            tp.setParameter("ville", pVille);
            return tp.getResultList();
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Club> findClubByNomWithJudokas(String pName) throws DaoException {
        try {
            TypedQuery<Club> tp = this.em.createQuery(
                    "SELECT c FROM Club c " +
                    "WHERE c.membres IN (SELECT j FROM Judoka j" +
                                        " WHERE j.nom = :nom)", Club.class);
            tp.setParameter("nom", pName);
            return Optional.ofNullable(tp.getSingleResult());
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}