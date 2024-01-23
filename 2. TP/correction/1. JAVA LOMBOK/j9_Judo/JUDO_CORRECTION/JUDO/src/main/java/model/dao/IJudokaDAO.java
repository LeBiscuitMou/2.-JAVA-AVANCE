package model.dao;

import model.dao.base.BaseDao;
import model.entities.Competition;
import model.entities.Judoka;
import model.entities.references.Categorie;
import model.entities.references.Grade;
import model.exceptions.DaoException;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IJudokaDAO extends BaseDao<Judoka, Serializable> {


    List<Judoka> findAllJudokaByNom(final String pNom) throws DaoException;

    List<Judoka> findAllJudokaByGrade(final Grade pGrade) throws DaoException;

    List<Judoka> findAllJudokaByCategorie(final Categorie categorie) throws DaoException;

    Optional<Judoka> findJudokaByPalmares(Competition pCompetition, Integer pClassement) throws DaoException;

    List<Judoka> findAllJudokaByCompetition(Competition pCompetition) throws DaoException;

}
