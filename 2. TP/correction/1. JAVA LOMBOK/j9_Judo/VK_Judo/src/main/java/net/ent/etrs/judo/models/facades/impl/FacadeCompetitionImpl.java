package net.ent.etrs.judo.models.facades.impl;

import net.ent.etrs.judo.models.commons.CoUtils;
import net.ent.etrs.judo.models.dao.IDaoCompetition;
import net.ent.etrs.judo.models.dao.exceptions.DaoException;
import net.ent.etrs.judo.models.dao.impl.DaoFactory;
import net.ent.etrs.judo.models.entities.Competition;
import net.ent.etrs.judo.models.entities.references.Ville;
import net.ent.etrs.judo.models.exceptions.BusinessException;
import net.ent.etrs.judo.models.facades.interfaces.FacadeCompetition;

import java.util.List;
import java.util.Optional;

public class FacadeCompetitionImpl extends GenericFacadeImpl<Competition> implements FacadeCompetition {
    protected IDaoCompetition daoCompetition;

    protected FacadeCompetitionImpl() {
        super(DaoFactory.getDaoCompetition());
        daoCompetition = DaoFactory.getDaoCompetition();
    }

    @Override
    public Competition saveCompetition(Competition pCompetition) throws BusinessException {
        return super.save(pCompetition);
    }

    @Override
    public Optional<Competition> findCompetition(Long pId) throws BusinessException {
        return super.find(pId);
    }

    @Override
    public List<Competition> findAllCompetition() throws BusinessException {
        return CoUtils.iterableToList(super.findAll());
    }

    @Override
    public void deleteCompetition(Long pId) throws BusinessException {
        super.delete(pId);
    }

    /**
     * Trouve toutes les compétitions ayant le nom voulu.
     *
     * @param pNom le nom voulu
     * @return une liste de compétition
     * @throws BusinessException ERREUR BDD
     */
    @Override
    public List<Competition> findAllCompetitionByNom(String pNom) throws BusinessException {
        try {
            return daoCompetition.findAllCompetitionByNom(pNom);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Trouve toutes les compétitions ayant eu lieu dans une année choisie.
     *
     * @param pAnnee l'année choisie
     * @return une liste de compétition
     * @throws BusinessException ERREUR BDD
     */
    @Override
    public List<Competition> findAllCompetitionByAnnee(int pAnnee) throws BusinessException {
        try {
            return daoCompetition.findAllCompetitionByAnnee(pAnnee);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Trouve toutes les compétitions ayant eu lieu dans une ville choisi.
     *
     * @param pVille la ville choisie
     * @return une liste de compétition
     * @throws BusinessException ERREUR BDD
     */
    @Override
    public List<Competition> findAllCompetitionByVille(Ville pVille) throws BusinessException {
        try {
            return daoCompetition.findAllCompetitionByVille(pVille);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Trouve toutes les compétitions s'étant déroulé dans une plage d'année choisie.
     *
     * @param anneeDebut le début de la plage d'années.
     * @param anneeFin la fin de la plage d'années.
     * @return une liste de compétition
     * @throws BusinessException ERREUR BDD
     */
    @Override
    public List<Competition> findAllCompetitionInAnnees(int anneeDebut, int anneeFin) throws BusinessException {
        try {
            return daoCompetition.findAllCompetitionInAnnees(anneeDebut, anneeFin);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }
}
