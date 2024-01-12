package net.ent.etrs.judo.models.dao;

import net.ent.etrs.judo.models.dao.exceptions.DaoException;
import net.ent.etrs.judo.models.entities.Competition;
import net.ent.etrs.judo.models.entities.references.Ville;

import java.util.List;

public interface IDaoCompetition extends BaseDao<Competition> {
    /**
     * Trouve toutes les compétitions ayant le nom voulu.
     *
     * @param pNom le nom voulu
     * @return une liste de compétition
     * @throws DaoException ERREUR BDD
     */
    List<Competition> findAllCompetitionByNom(String pNom) throws DaoException;

    /**
     * Trouve toutes les compétitions ayant eu lieu dans une année choisie.
     *
     * @param pAnnee l'année choisie
     * @return une liste de compétition
     * @throws DaoException ERREUR BDD
     */
    List<Competition> findAllCompetitionByAnnee(int pAnnee) throws DaoException;

    /**
     * Trouve toutes les compétitions ayant eu lieu dans une ville choisi.
     *
     * @param pVille la ville choisie
     * @return une liste de compétition
     * @throws DaoException ERREUR BDD
     */
    List<Competition> findAllCompetitionByVille(Ville pVille) throws DaoException;

    /**
     * Trouve toutes les compétitions s'étant déroulé dans une plage d'année choisie.
     *
     * @param anneeDebut le début de la plage d'années.
     * @param anneeFin la fin de la plage d'années.
     * @return une liste de compétition
     * @throws DaoException ERREUR BDD
     */
    List<Competition> findAllCompetitionInAnnees(int anneeDebut, int anneeFin) throws DaoException;
}
