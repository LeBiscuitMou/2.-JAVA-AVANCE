package net.ent.etrs.projet.models.dao;

import net.ent.etrs.projet.models.dao.exceptions.DaoException;
import net.ent.etrs.projet.models.entities.Competition;
import net.ent.etrs.projet.models.references.Ville;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface DaoCompetition extends BaseDao<Competition, Serializable> {
    Optional<Competition> getCompetition(Long pId) throws DaoException;

    List<Competition> getAllCompetitionByNom(String pNom) throws DaoException;

    List<Competition> getAllCompetitionByAnnee(int pAnnee) throws DaoException;

    List<Competition> getAllCompetitionByVille(Ville pVille) throws DaoException;

    List<Competition> getAllCompetitionInAnnees(int anneeDebut, int anneeFin);
}
