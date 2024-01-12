package model.dao.impl;

import model.dao.IJudokaDAO;
import model.dao.base.AbstractJpaDao;
import model.entities.Competition;
import model.entities.Judoka;
import model.entities.references.Categorie;
import model.entities.references.Grade;
import model.exceptions.DaoException;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class JudokaDAO extends AbstractJpaDao<Judoka, Long> implements IJudokaDAO {
    @Override
    public List<Judoka> findAllJudokaByNom(String pNom) throws DaoException {
        try {
            return em.createQuery("SELECT j FROM Judoka j WHERE j.nom=:nom", Judoka.class)
                    .setParameter("nom", pNom)
                    .getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Judoka> findAllJudokaByGrade(Grade pGrade) throws DaoException {
        try {
            return em.createQuery("SELECT j FROM Judoka j WHERE j.grade=:grade", Judoka.class)
                    .setParameter("grade", pGrade)
                    .getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Judoka> findAllJudokaByCategorie(Categorie categorie) throws DaoException {
        try {
            return em.createQuery("SELECT j FROM Judoka j WHERE j.categorie=:cat", Judoka.class)
                    .setParameter("cat", categorie)
                    .getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Judoka> findJudokaByPalmares(Competition pCompetition, Integer pClassement) throws DaoException {
        TypedQuery<Judoka> q = em.createQuery("SELECT j FROM Judoka j WHERE key(j.palmares) = :palmares AND :classement IN(value(j.palmares)) ", Judoka.class);
        q.setParameter("palmares", pCompetition);
        q.setParameter("classement", pClassement);

        try {
            return Optional.of(q.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Judoka> findAllJudokaByCompetition(Competition pCompetition) throws DaoException {
        try {
            return em.createQuery("SELECT j FROM Judoka j WHERE key(j.palmares) = :competition", Judoka.class)
                    .setParameter("competition", pCompetition)
                    .getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
