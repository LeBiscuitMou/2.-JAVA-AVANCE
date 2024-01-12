package net.ent.etrs.judo.models.dao.impl;

import net.ent.etrs.judo.models.dao.IDaoJudoka;
import net.ent.etrs.judo.models.dao.exceptions.DaoException;
import net.ent.etrs.judo.models.entities.Competition;
import net.ent.etrs.judo.models.entities.Judoka;
import net.ent.etrs.judo.models.entities.references.Categorie;
import net.ent.etrs.judo.models.entities.references.Grade;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class DaoJudokaJpaImpl extends JpaBaseDao<Judoka> implements IDaoJudoka {

    /**
     * Trouve tous les judokas ayant le nom de famille choisi.
     *
     * @param pNom le nom choisi
     * @return une liste de judoka
     * @throws DaoException BDD
     */
    @Override
    public List<Judoka> findAllJudokaByNom(String pNom) throws DaoException {
        try {
            TypedQuery<Judoka> query = this.em.createQuery("SELECT t " +
                            "FROM Judoka t " +
                            "WHERE t.nom = :nom"
                    , Judoka.class);
            query.setParameter("nom", pNom);

            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }

    }

    /**
     * Trouve tous les judokas ayant le grade choisi.
     *
     * @param pGrade le grade choisi
     * @return une liste de judoka
     * @throws DaoException ERREUR BDD
     */
    @Override
    public List<Judoka> findAllByGrade(Grade pGrade) throws DaoException {
        try {
            TypedQuery<Judoka> query = this.em.createQuery("SELECT t " +
                            "FROM Judoka t " +
                            "WHERE t.grade = :grade"
                    , Judoka.class);
            query.setParameter("grade", pGrade);

            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }

    }

    /**
     * Trouve tous les judokas ayant la catégorie choisie.
     *
     * @param pCategorie la catégorie choisie
     * @return une liste de judoka
     * @throws DaoException ERREUR BDD
     */
    @Override
    public List<Judoka> findAllByCategorie(Categorie pCategorie) throws DaoException {
        try {
            TypedQuery<Judoka> query = this.em.createQuery("SELECT t " +
                            "FROM Judoka t " +
                            "WHERE t.categorie = :categorie"
                    , Judoka.class);
            query.setParameter("categorie", pCategorie);

            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }

    }

    /**
     * Trouve le judoka ayant participé à la compétition choisie et étant placé dans le classement choisi.
     *
     * @param pCompetition la compétition choisie
     * @param pClassement  le classement choisi
     * @return un judoka optional
     * @throws DaoException ERREUR BDD
     */
    @Override
    public Optional<Judoka> findJudokaByPalmares(Competition pCompetition, Integer pClassement) throws DaoException {
        try {
            TypedQuery<Judoka> query = this.em.createQuery("SELECT t " +
                            "FROM Judoka t " +
                            "WHERE KEY(t.palmares) = :comp AND VALUE(t.palmares) = :classement"
                    , Judoka.class);
            query.setParameter("comp", pCompetition);
            query.setParameter("classement", pClassement);

            List<Judoka> list = query.getResultList();
            if(list.isEmpty()){
                return Optional.empty();
            }
            return Optional.ofNullable(list.get(0));

        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Trouve tous les judokas ayant participé à la compétition choisie.
     *
     * @param pCompetition la compétition choisie
     * @return une liste de judoka
     * @throws DaoException ERREUR BDD
     */
    @Override
    public List<Judoka> findAllJudokaByCompetition(Competition pCompetition) throws DaoException {
        try {
            TypedQuery<Judoka> query = this.em.createQuery("SELECT t " +
                            "FROM Judoka t " +
                            "WHERE KEY(t.palmares) = :comp"
                    , Judoka.class);
            query.setParameter("comp", pCompetition);

            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }

    }
}
