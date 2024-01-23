package net.ent.etrs.judo.models.facades.impl;

import net.ent.etrs.judo.models.commons.CoUtils;
import net.ent.etrs.judo.models.dao.IDaoJudoka;
import net.ent.etrs.judo.models.dao.exceptions.DaoException;
import net.ent.etrs.judo.models.dao.impl.DaoFactory;
import net.ent.etrs.judo.models.entities.Competition;
import net.ent.etrs.judo.models.entities.Judoka;
import net.ent.etrs.judo.models.entities.references.Categorie;
import net.ent.etrs.judo.models.entities.references.Grade;
import net.ent.etrs.judo.models.exceptions.BusinessException;
import net.ent.etrs.judo.models.facades.interfaces.FacadeJudoka;

import java.util.List;
import java.util.Optional;

public class FacadeJudokaImpl extends GenericFacadeImpl<Judoka> implements FacadeJudoka {
    protected IDaoJudoka daoJudoka;

    protected FacadeJudokaImpl() {
        super(DaoFactory.getDaoJudoka());
        daoJudoka = DaoFactory.getDaoJudoka();
    }

    @Override
    public Judoka saveJudoka(Judoka pJudoka) throws BusinessException {
        return super.save(pJudoka);
    }

    @Override
    public Optional<Judoka> findJudoka(Long pId) throws BusinessException {
        return super.find(pId);
    }

    @Override
    public List<Judoka> findAllJudoka() throws BusinessException {
        return CoUtils.iterableToList(super.findAll());
    }

    @Override
    public void deleteJudoka(Long pId) throws BusinessException {
        super.delete(pId);
    }

    /**
     * Trouve tous les judokas ayant le nom de famille choisi.
     *
     * @param pNom le nom choisi
     * @return une liste de judoka
     * @throws BusinessException ERREUR BDD
     */
    @Override
    public List<Judoka> findAllJudokaByNom(String pNom) throws BusinessException {
        try {
            return daoJudoka.findAllJudokaByNom(pNom);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Trouve tous les judokas ayant le grade choisi.
     *
     * @param pGrade le grade choisi
     * @return une liste de judoka
     * @throws BusinessException ERREUR BDD
     */
    @Override
    public List<Judoka> findAllByGrade(Grade pGrade) throws BusinessException {
        try {
            return daoJudoka.findAllByGrade(pGrade);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Trouve tous les judokas ayant la catégorie choisie.
     *
     * @param pCategorie la catégorie choisie
     * @return une liste de judoka
     * @throws BusinessException ERREUR BDD
     */
    @Override
    public List<Judoka> findAllByCategorie(Categorie pCategorie) throws BusinessException {
        try {
            return daoJudoka.findAllByCategorie(pCategorie);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Trouve le judoka ayant participé à la compétition choisie et étant placé dans le classement choisi.
     *
     * @param pCompetition la compétition choisie
     * @param pClassement  le classement choisi
     * @return un judoka optional
     * @throws BusinessException ERREUR BDD
     */
    @Override
    public Optional<Judoka> findJudokaByPalmares(Competition pCompetition, Integer pClassement) throws BusinessException {
        try {
            return daoJudoka.findJudokaByPalmares(pCompetition, pClassement);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Trouve tous les judokas ayant participé à la compétition choisie.
     *
     * @param pCompetition la compétition choisie
     * @return une liste de judoka
     * @throws BusinessException ERREUR BDD
     */
    @Override
    public List<Judoka> findAllJudokaByCompetition(Competition pCompetition) throws BusinessException {
        try {
            return daoJudoka.findAllJudokaByCompetition(pCompetition);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }


}
