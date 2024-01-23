package model.dao;

import model.dao.base.BaseDao;
import model.entities.Club;
import model.entities.references.Ville;
import model.exceptions.DaoException;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IClubDAO extends BaseDao<Club, Serializable> {

    Optional<Club> findClubByNomWithJudokas(String pName) throws DaoException;

    List<Club> findAllClubByVille(Ville pVille) throws DaoException;
}
