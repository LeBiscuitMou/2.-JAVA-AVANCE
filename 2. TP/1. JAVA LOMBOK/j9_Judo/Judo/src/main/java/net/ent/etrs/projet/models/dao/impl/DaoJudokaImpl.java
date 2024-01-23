package net.ent.etrs.projet.models.dao.impl;

import net.ent.etrs.projet.models.dao.DaoJudoka;
import net.ent.etrs.projet.models.dao.exceptions.DaoException;
import net.ent.etrs.projet.models.entities.Competition;
import net.ent.etrs.projet.models.entities.Judoka;
import net.ent.etrs.projet.models.references.Categorie;
import net.ent.etrs.projet.models.references.Grade;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DaoJudokaImpl extends AbstractJpaDao<Judoka, Serializable> implements DaoJudoka {
    @Override
    public Optional<Judoka> getJudoka(Long pId) throws DaoException {
        try {
            TypedQuery<Judoka> tp = this.em.createQuery(
                    "SELECT j FROM Judoka j " +
                            "WHERE j.id = :id", Judoka.class);
            tp.setParameter("id", pId);

            return Optional.ofNullable(tp.getSingleResult());
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public List<Judoka> getAllJudokaByNom(String pNom) throws DaoException {
        try {
            TypedQuery<Judoka> tp = this.em.createQuery(
                    "SELECT j FROM Judoka j " +
                    "WHERE j.nom = :nom", Judoka.class);
            tp.setParameter("nom", pNom);
            return Collections.unmodifiableList(tp.getResultList());
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public List<Judoka> getAllByGrade(Grade pGrade) throws DaoException {
        try {
            TypedQuery<Judoka> tp = this.em.createQuery(
                    "SELECT j FROM Judoka j " +
                    "WHERE j.grade = :grade", Judoka.class);
            tp.setParameter("grade", pGrade);
            return Collections.unmodifiableList(tp.getResultList());
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public List<Judoka> getAllByCategorie(Categorie pCategorie) throws DaoException {
        try {
            TypedQuery<Judoka> tp = this.em.createQuery(
                    "SELECT j FROM Judoka j " +
                            "WHERE j.categorie = :categorie", Judoka.class);
            tp.setParameter("categorie", pCategorie);
            return Collections.unmodifiableList(tp.getResultList());
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Judoka> getJudokaByPalmares(Competition pCompetition, Integer pClassement) throws DaoException {
        try {
            TypedQuery<Judoka> tp = this.em.createQuery(
                    "SELECT j FROM Judoka j " +
                    "WHERE KEY(j.palmares) = :competition " +
                    "AND VALUE(j.palmares) = :classement", Judoka.class);
            tp.setParameter("competition", pCompetition);
            tp.setParameter("classement", pClassement);
            return Optional.ofNullable(tp.getSingleResult());
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public List<Judoka> getAllJudokaByCompetition(Competition pCompetition) throws DaoException {
        try {
            TypedQuery<Judoka> tp = this.em.createQuery(
                    "SELECT j FROM Judoka j " +
                    "WHERE KEY(j.palmares) = :competition", Judoka.class);
            tp.setParameter("competition", pCompetition);
            return Collections.unmodifiableList(tp.getResultList());
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}