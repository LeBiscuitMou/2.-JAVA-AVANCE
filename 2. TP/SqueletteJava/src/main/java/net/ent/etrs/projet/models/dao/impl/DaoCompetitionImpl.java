package net.ent.etrs.projet.models.dao.impl;

import net.ent.etrs.projet.models.dao.DaoCompetition;
import net.ent.etrs.projet.models.dao.DaoJudoka;
import net.ent.etrs.projet.models.dao.exceptions.DaoException;
import net.ent.etrs.projet.models.entities.Competition;
import net.ent.etrs.projet.models.entities.Judoka;
import net.ent.etrs.projet.models.references.Ville;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DaoCompetitionImpl extends AbstractJpaDao<Competition, Serializable> implements DaoCompetition {
    @Override
    public Optional<Competition> getCompetition(Long pId) throws DaoException {
        try {
            TypedQuery<Competition> query = this.em.createQuery(
                    "SELECT c FROM Competition c " +
                            "WHERE c.id = " + pId, Competition.class);

            return Optional.ofNullable(query.getSingleResult());
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public List<Competition> getAllCompetitionByNom(String pNom) throws DaoException {
        try {
            TypedQuery<Competition> query = this.em.createQuery(
                    "SELECT c FROM Competition c " +
                    "WHERE c.nom = :nom", Competition.class);
            query.setParameter("nom", pNom);
            return Collections.unmodifiableList(query.getResultList());
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public List<Competition> getAllCompetitionByAnnee(int pAnnee) throws DaoException {
        try {
            TypedQuery<Competition> query = this.em.createQuery(
                    "SELECT c FROM Competition c " +
                    "WHERE c.annee = :annee", Competition.class);
            query.setParameter("annee", pAnnee);
            return Collections.unmodifiableList(query.getResultList());
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public List<Competition> getAllCompetitionByVille(Ville pVille) throws DaoException {
        try {
            TypedQuery<Competition> query = this.em.createQuery(
                    "SELECT c FROM Competition c " +
                            "WHERE c.ville = :ville", Competition.class);
            query.setParameter("ville", pVille);
            return Collections.unmodifiableList(query.getResultList());
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public List<Competition> getAllCompetitionInAnnees(int anneeDebut, int anneeFin) {
        return null;
    }
}