package net.ent.etrs.judo.models.dao.impl;

import net.ent.etrs.judo.models.dao.IDaoCompetition;
import net.ent.etrs.judo.models.dao.exceptions.DaoException;
import net.ent.etrs.judo.models.entities.Competition;
import net.ent.etrs.judo.models.entities.references.Ville;

import javax.persistence.TypedQuery;
import java.util.List;

public class DaoCompetitionJpaImpl extends JpaBaseDao<Competition> implements IDaoCompetition {

    /**
     * Trouve toutes les compétitions ayant le nom voulu.
     *
     * @param pNom le nom voulu
     * @return une liste de compétition
     * @throws DaoException ERREUR BDD
     */
    @Override
    public List<Competition> findAllCompetitionByNom(String pNom) throws DaoException {
        try {
            TypedQuery<Competition> query = this.em.createQuery("SELECT t " +
                            "FROM Competition t " +
                            "WHERE t.nom = :nom"
                    , Competition.class);
            query.setParameter("nom", pNom);

            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Trouve toutes les compétitions ayant eu lieu dans une année choisie.
     *
     * @param pAnnee l'année choisie
     * @return une liste de compétition
     * @throws DaoException ERREUR BDD
     */
    @Override
    public List<Competition> findAllCompetitionByAnnee(int pAnnee) throws DaoException {
        try {
            TypedQuery<Competition> query = this.em.createQuery("SELECT t " +
                            "FROM Competition t " +
                            "WHERE t.annee = :annee"
                    , Competition.class);
            query.setParameter("annee", pAnnee);

            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Trouve toutes les compétitions ayant eu lieu dans une ville choisi.
     *
     * @param pVille la ville choisie
     * @return une liste de compétition
     * @throws DaoException ERREUR BDD
     */
    @Override
    public List<Competition> findAllCompetitionByVille(Ville pVille) throws DaoException {
        try {
            TypedQuery<Competition> query = this.em.createQuery("SELECT t " +
                            "FROM Competition t " +
                            "WHERE t.ville = :ville"
                    , Competition.class);
            query.setParameter("ville", pVille);

            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Trouve toutes les compétitions s'étant déroulé dans une plage d'année choisie.
     *
     * @param anneeDebut le début de la plage d'années.
     * @param anneeFin   la fin de la plage d'années.
     * @return une liste de compétition
     * @throws DaoException ERREUR BDD
     */
    @Override
    public List<Competition> findAllCompetitionInAnnees(int anneeDebut, int anneeFin) throws DaoException {
        try {
            TypedQuery<Competition> query = this.em.createQuery("SELECT t " +
                            "FROM Competition t " +
                            "WHERE t.annee BETWEEN :anneeDebut AND :anneeFin"
                    , Competition.class);
            query.setParameter("anneeDebut", anneeDebut);
            query.setParameter("anneeFin", anneeFin);

            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }
}
