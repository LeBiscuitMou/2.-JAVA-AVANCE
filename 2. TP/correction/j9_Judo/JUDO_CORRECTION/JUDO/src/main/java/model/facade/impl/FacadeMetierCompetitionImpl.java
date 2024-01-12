package model.facade.impl;

import model.dao.DaoFactory;
import model.dao.ICompetitionDAO;
import model.entities.Competition;
import model.entities.references.Ville;
import model.exceptions.DaoException;
import model.facade.IFacadeMetierCompetition;
import model.facade.exceptions.CompetitionException;
import org.apache.commons.collections4.IterableUtils;

import java.util.List;
import java.util.Optional;

public class FacadeMetierCompetitionImpl implements IFacadeMetierCompetition {
    private final ICompetitionDAO daoCompetition = DaoFactory.getCompetitionDao();
    
    protected FacadeMetierCompetitionImpl() {
    }
    
    @Override
    public Competition saveCompetition(Competition pCompetition) throws CompetitionException {
        try {
            return this.daoCompetition.save(pCompetition);
        } catch (DaoException e) {
            throw new CompetitionException(e);
        }
    }
    
    @Override
    public Optional<Competition> findCompetition(Long pId) throws CompetitionException {
        try {
            return this.daoCompetition.find(pId);
        } catch (DaoException e) {
            throw new CompetitionException(e);
        }
    }
    
    @Override
    public List<Competition> findAllCompetition() throws CompetitionException {
        try {
            return IterableUtils.toList(this.daoCompetition.findAll());
        } catch (DaoException e) {
            throw new CompetitionException();
        }
    }
    
    @Override
    public void deleteCompetition(Long pId) throws CompetitionException {
        try {
            this.daoCompetition.delete(pId);
        } catch (DaoException e) {
            throw new CompetitionException(e);
        }
    }
    
    @Override
    public List<Competition> findAllCompetitionByNom(String pNom) throws CompetitionException {
        try {
            return this.daoCompetition.findAllCompetitionByNom(pNom);
        } catch (DaoException e) {
            throw new CompetitionException(e);
        }
    }
    
    @Override
    public List<Competition> findAllCompetitionByAnnee(int pAnnee) throws CompetitionException {
        try {
            return this.daoCompetition.findAllCompetitionByAnnee(pAnnee);
        } catch (DaoException e) {
            throw new CompetitionException(e);
        }
    }
    
    @Override
    public List<Competition> findAllCompetitionByVille(Ville pVille) throws CompetitionException {
        try {
            return this.daoCompetition.findAllCompetitionByVille(pVille);
        } catch (DaoException e) {
            throw new CompetitionException(e);
        }
    }
    
    @Override
    public List<Competition> findAllCompetitionInAnnees(int anneeDebut, int anneeFin) throws CompetitionException {
        try {
            return this.daoCompetition.findAllCompetitionInAnnees(anneeDebut, anneeFin);
        } catch (DaoException e) {
            throw new CompetitionException(e);
        }
    }
}
