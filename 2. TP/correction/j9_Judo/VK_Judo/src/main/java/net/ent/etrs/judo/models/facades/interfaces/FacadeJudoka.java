package net.ent.etrs.judo.models.facades.interfaces;

import net.ent.etrs.judo.models.entities.Competition;
import net.ent.etrs.judo.models.entities.Judoka;
import net.ent.etrs.judo.models.entities.references.Categorie;
import net.ent.etrs.judo.models.entities.references.Grade;
import net.ent.etrs.judo.models.exceptions.BusinessException;

import java.util.List;
import java.util.Optional;

public interface FacadeJudoka extends GenericFacade<Judoka> {
    Judoka saveJudoka(final Judoka pJudoka) throws BusinessException;

    Optional<Judoka> findJudoka(final Long pId) throws BusinessException;

    List<Judoka> findAllJudoka() throws BusinessException;

    void deleteJudoka(final Long pId) throws BusinessException;

    /**
     * Trouve tous les judokas ayant le nom de famille choisi.
     *
     * @param pNom le nom choisi
     * @return une liste de judoka
     * @throws BusinessException ERREUR BDD
     */
    List<Judoka> findAllJudokaByNom(final String pNom) throws BusinessException;

    /**
     * Trouve tous les judokas ayant le grade choisi.
     *
     * @param pGrade le grade choisi
     * @return une liste de judoka
     * @throws BusinessException ERREUR BDD
     */
    List<Judoka> findAllByGrade(final Grade pGrade) throws BusinessException;

    /**
     * Trouve tous les judokas ayant la catégorie choisie.
     *
     * @param pCategorie la catégorie choisie
     * @return une liste de judoka
     * @throws BusinessException ERREUR BDD
     */
    List<Judoka> findAllByCategorie(final Categorie pCategorie) throws BusinessException;

    /**
     * Trouve le judoka ayant participé à la compétition choisie et étant placé dans le classement choisi.
     *
     * @param pCompetition la compétition choisie
     * @param pClassement le classement choisi
     * @return un judoka optional
     * @throws BusinessException ERREUR BDD
     */
    Optional<Judoka> findJudokaByPalmares(final Competition pCompetition, final Integer pClassement) throws BusinessException;

    /**
     * Trouve tous les judokas ayant participé à la compétition choisie.
     *
     * @param pCompetition la compétition choisie
     * @return une liste de judoka
     * @throws BusinessException ERREUR BDD
     */
    List<Judoka> findAllJudokaByCompetition(final Competition pCompetition) throws BusinessException;
}
