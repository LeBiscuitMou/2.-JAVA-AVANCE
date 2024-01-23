package net.ent.etrs.projet.models.dao;

import net.ent.etrs.projet.models.dao.exceptions.DaoException;
import net.ent.etrs.projet.models.entities.Club;
import net.ent.etrs.projet.models.references.Ville;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface DaoClub extends BaseDao<Club, Serializable> {
    Optional<Club> getClub(Long pId) throws DaoException;

    List<Club> findAllClubByVille(Ville pVille) throws DaoException;

    Optional<Club> findClubByNomWithJudokas(String pName) throws DaoException;
}
