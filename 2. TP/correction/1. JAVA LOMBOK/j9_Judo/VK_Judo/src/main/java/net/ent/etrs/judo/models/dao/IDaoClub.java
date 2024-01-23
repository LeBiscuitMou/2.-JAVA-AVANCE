package net.ent.etrs.judo.models.dao;

import net.ent.etrs.judo.models.dao.exceptions.DaoException;
import net.ent.etrs.judo.models.entities.Club;
import net.ent.etrs.judo.models.entities.references.Ville;

import java.util.List;
import java.util.Optional;

public interface IDaoClub extends BaseDao<Club> {

    /**
     * Trouve le club ayant le nom voulu et qui ont au moins 1 judoka.
     *
     * @param pName le nom du club
     * @return un club ou null si le club n'existe pas
     * @throws DaoException erreur BDD
     */
    Optional<Club> findClubByNomWithJudokas(String pName) throws DaoException;

    /**
     * Trouve tous les clubs basés dans la ville choisis
     *
     * @param pVille la ville choisis
     * @return une liste de clubs
     * @throws DaoException erreur BDD
     */
    List<Club> findAllClubByVille(Ville pVille) throws DaoException;

}
