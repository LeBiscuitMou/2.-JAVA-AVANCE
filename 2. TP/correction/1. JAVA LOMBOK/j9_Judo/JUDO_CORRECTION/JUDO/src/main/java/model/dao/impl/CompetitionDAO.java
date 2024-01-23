package model.dao.impl;

import model.dao.ICompetitionDAO;
import model.dao.base.JpaBaseDao;
import model.entities.Competition;
import model.entities.references.Ville;
import model.exceptions.DaoException;

import java.util.List;

public class CompetitionDAO extends JpaBaseDao<Competition, Long> implements ICompetitionDAO {
    @Override
    public List<Competition> findAllCompetitionByNom(String pNom) throws DaoException {
        try {
            return em.createQuery("SELECT c FROM Competition c WHERE c.nom = :nom", Competition.class)
                    .setParameter("nom", pNom)
                    .getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Competition> findAllCompetitionByAnnee(int pAnnee) throws DaoException {
        try {
            return em.createQuery("SELECT c FROM Competition c WHERE c.annee = :annee", Competition.class)
                    .setParameter("annee", pAnnee)
                    .getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Competition> findAllCompetitionByVille(Ville pVille) throws DaoException {
        try {
            return em.createQuery("SELECT c FROM Competition c WHERE c.ville = :ville", Competition.class)
                    .setParameter("ville", pVille)
                    .getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Competition> findAllCompetitionInAnnees(int anneeDebut, int anneeFin) throws DaoException {
        try {
            return em.createQuery("SELECT c FROM Competition c WHERE c.annee BETWEEN :anneeDebut AND :anneeFin ", Competition.class)
                    .setParameter("anneeDebut", anneeDebut)
                    .setParameter("anneeFin", anneeFin)
                    .getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
