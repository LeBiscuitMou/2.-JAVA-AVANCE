package net.ent.etrs.judo.models.dao;

import net.ent.etrs.judo.models.dao.exceptions.DaoException;
import net.ent.etrs.judo.models.entities.Competition;
import net.ent.etrs.judo.models.entities.Judoka;
import net.ent.etrs.judo.models.entities.references.Categorie;
import net.ent.etrs.judo.models.entities.references.Grade;
import net.ent.etrs.judo.models.exceptions.BusinessException;

import java.util.List;
import java.util.Optional;

public interface IDaoJudoka extends BaseDao<Judoka> {
    /**
     * Trouve tous les judokas ayant le nom de famille choisi.
     *
     * @param pNom le nom choisi
     * @return une liste de judoka
     * @throws DaoException BDD
     */
    List<Judoka> findAllJudokaByNom(String pNom) throws DaoException;

    /**
     * Trouve tous les judokas ayant le grade choisi.
     *
     * @param pGrade le grade choisi
     * @return une liste de judoka
     * @throws DaoException ERREUR BDD
     */
    List<Judoka> findAllByGrade(Grade pGrade) throws DaoException;

    /**
     * Trouve tous les judokas ayant la catégorie choisie.
     *
     * @param pCategorie la catégorie choisie
     * @return une liste de judoka
     * @throws DaoException ERREUR BDD
     */
    List<Judoka> findAllByCategorie(Categorie pCategorie) throws DaoException;

    /**
     * Trouve le judoka ayant participé à la compétition choisie et étant placé dans le classement choisi.
     *
     * @param pCompetition la compétition choisie
     * @param pClassement  le classement choisi
     * @return un judoka optional
     * @throws DaoException ERREUR BDD
     */
    Optional<Judoka> findJudokaByPalmares(Competition pCompetition, Integer pClassement) throws DaoException;

    /**
     * Trouve tous les judokas ayant participé à la compétition choisie.
     *
     * @param pCompetition la compétition choisie
     * @return une liste de judoka
     * @throws DaoException ERREUR BDD
     */
    List<Judoka> findAllJudokaByCompetition(Competition pCompetition) throws DaoException;
}
