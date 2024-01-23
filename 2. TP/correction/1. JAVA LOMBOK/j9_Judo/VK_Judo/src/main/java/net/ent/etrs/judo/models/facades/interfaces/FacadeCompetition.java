package net.ent.etrs.judo.models.facades.interfaces;

import net.ent.etrs.judo.models.dao.exceptions.DaoException;
import net.ent.etrs.judo.models.entities.Competition;
import net.ent.etrs.judo.models.entities.references.Ville;
import net.ent.etrs.judo.models.exceptions.BusinessException;

import java.util.List;
import java.util.Optional;

public interface FacadeCompetition extends GenericFacade<Competition> {
    Competition saveCompetition(final Competition pCompetition) throws BusinessException;

    Optional<Competition> findCompetition(final Long pId) throws BusinessException;

    List<Competition> findAllCompetition() throws BusinessException;

    void deleteCompetition(final Long pId) throws BusinessException;

    /**
     * Trouve toutes les compétitions ayant le nom voulu.
     *
     * @param pNom le nom voulu
     * @return une liste de compétition
     * @throws BusinessException ERREUR BDD
     */
    List<Competition> findAllCompetitionByNom(final String pNom) throws BusinessException;

    /**
     * Trouve toutes les compétitions ayant eu lieu dans une année choisie.
     *
     * @param pAnnee l'année choisie
     * @return une liste de compétition
     * @throws BusinessException ERREUR BDD
     */
    List<Competition> findAllCompetitionByAnnee(final int pAnnee) throws BusinessException;

    /**
     * Trouve toutes les compétitions ayant eu lieu dans une ville choisi.
     *
     * @param pVille la ville choisie
     * @return une liste de compétition
     * @throws BusinessException ERREUR BDD
     */
    List<Competition> findAllCompetitionByVille(final Ville pVille) throws BusinessException;

    /**
     * Trouve toutes les compétitions s'étant déroulé dans une plage d'année choisie.
     *
     * @param anneeDebut le début de la plage d'années.
     * @param anneeFin la fin de la plage d'années.
     * @return une liste de compétition
     * @throws BusinessException ERREUR BDD
     */
    List<Competition> findAllCompetitionInAnnees(final int anneeDebut, final int anneeFin) throws BusinessException;
}
