package model.dao;

import model.dao.base.BaseDao;
import model.entities.Competition;
import model.entities.references.Ville;
import model.exceptions.DaoException;

import java.io.Serializable;
import java.util.List;

public interface ICompetitionDAO extends BaseDao<Competition, Serializable> {

    List<Competition> findAllCompetitionByNom(String pNom) throws DaoException;

    List<Competition> findAllCompetitionByAnnee(int pAnnee) throws DaoException;

    List<Competition> findAllCompetitionByVille(Ville pVille) throws DaoException;

    List<Competition> findAllCompetitionInAnnees(int anneeDebut, int anneeFin) throws DaoException;
}
