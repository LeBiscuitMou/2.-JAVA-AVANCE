package model.facade.impl;

import model.dao.DaoFactory;
import model.dao.IJudokaDAO;
import model.entities.Competition;
import model.entities.Judoka;
import model.entities.references.Categorie;
import model.entities.references.Grade;
import model.exceptions.DaoException;
import model.facade.IFacadeMetierJudoka;
import model.facade.exceptions.JudokaException;
import org.apache.commons.collections4.IterableUtils;

import java.util.List;
import java.util.Optional;

public class FacadeMetierJudokaImpl implements IFacadeMetierJudoka {
    private final IJudokaDAO daoJudoka = DaoFactory.getJudokaDao();
    
    protected FacadeMetierJudokaImpl() {
    }
    
    @Override
    public Judoka saveJudoka(Judoka pJudoka) throws JudokaException {
        try {
            return this.daoJudoka.save(pJudoka);
        } catch (DaoException e) {
            throw new JudokaException(e);
        }
    }
    
    @Override
    public Optional<Judoka> findJudoka(Long pId) throws JudokaException {
        try {
            return this.daoJudoka.find(pId);
        } catch (DaoException e) {
            throw new JudokaException(e);
        }
    }
    
    @Override
    public List<Judoka> findAllJudoka() throws JudokaException {
        try {
            return IterableUtils.toList(this.daoJudoka.findAll());
        } catch (DaoException e) {
            throw new JudokaException(e);
        }
    }
    
    @Override
    public void deleteJudoka(Long pId) throws JudokaException {
        try {
            this.daoJudoka.delete(pId);
        } catch (DaoException e) {
            throw new JudokaException(e);
        }
    }
    
    @Override
    public List<Judoka> findAllJudokaByNom(String pNom) throws JudokaException {
        try {
            return this.daoJudoka.findAllJudokaByNom(pNom);
        } catch (DaoException e) {
            throw new JudokaException(e);
        }
    }
    
    @Override
    public List<Judoka> findAllByGrade(Grade pGrade) throws JudokaException {
        try {
            return this.daoJudoka.findAllJudokaByGrade(pGrade);
        } catch (DaoException e) {
            throw new JudokaException(e);
        }
    }
    
    @Override
    public List<Judoka> findAllByCategorie(Categorie pCategorie) throws JudokaException {
        try {
            return this.daoJudoka.findAllJudokaByCategorie(pCategorie);
        } catch (DaoException e) {
            throw new JudokaException(e);
        }
    }
    
    @Override
    public Optional<Judoka> findJudokaByPalmares(Competition pCompetition, Integer pClassement) throws JudokaException {
        try {
            return this.daoJudoka.findJudokaByPalmares(pCompetition, pClassement);
        } catch (DaoException e) {
            throw new JudokaException(e);
        }
    }
    
    @Override
    public List<Judoka> findAllJudokaByCompetition(Competition pCompetition) throws JudokaException {
        try {
            return this.daoJudoka.findAllJudokaByCompetition(pCompetition);
        } catch (DaoException e) {
            throw new JudokaException(e);
        }
    }
}
