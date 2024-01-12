package net.ent.etrs.judo.models.facades.interfaces;

import net.ent.etrs.judo.models.dao.exceptions.DaoException;
import net.ent.etrs.judo.models.entities.Club;
import net.ent.etrs.judo.models.entities.references.Ville;
import net.ent.etrs.judo.models.exceptions.BusinessException;

import java.util.List;
import java.util.Optional;

public interface FacadeClub extends GenericFacade<Club> {
    Club saveClub(final Club pClub) throws BusinessException;

    Optional<Club> findClub(final Long pId) throws BusinessException;

    List<Club> findAllClub() throws BusinessException;

    void deleteClub(final Long pId) throws BusinessException;

    /**
     * Trouve le club ayant le nom voulu et qui ont au moins 1 judoka.
     *
     * @param pName le nom du club
     * @return un club ou null si le club n'existe pas
     * @throws BusinessException erreur BDD
     */
    Optional<Club> findClubByNomWithJudokas(final String pName) throws BusinessException;

    /**
     * Trouve tous les clubs basés dans la ville choisis
     *
     * @param pVille la ville choisis
     * @return une liste de clubs
     * @throws BusinessException erreur BDD
     */
    List<Club> findAllClubByVille(final Ville pVille) throws BusinessException;
}
