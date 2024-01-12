package net.ent.etrs.projet.models.dao;

import net.ent.etrs.projet.models.dao.exceptions.DaoException;
import net.ent.etrs.projet.models.entities.Competition;
import net.ent.etrs.projet.models.entities.Judoka;
import net.ent.etrs.projet.models.references.Categorie;
import net.ent.etrs.projet.models.references.Grade;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface DaoJudoka extends BaseDao<Judoka, Serializable> {
    Optional<Judoka> getJudoka(Long pId) throws DaoException;

    List<Judoka> getAllJudokaByNom(String pNom) throws DaoException;

    List<Judoka> getAllByGrade(Grade pGrade) throws DaoException;

    List<Judoka> getAllByCategorie(Categorie pCategorie) throws DaoException;

    Optional<Judoka> getJudokaByPalmares(Competition pCompetition, Integer pClassement) throws DaoException;

    List<Judoka> getAllJudokaByCompetition(Competition pCompetition) throws DaoException;
}
