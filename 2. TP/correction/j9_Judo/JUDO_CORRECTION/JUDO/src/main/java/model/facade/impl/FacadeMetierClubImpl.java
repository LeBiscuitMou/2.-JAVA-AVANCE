package model.facade.impl;

import model.dao.DaoFactory;
import model.dao.IClubDAO;
import model.entities.Club;
import model.entities.references.Ville;
import model.exceptions.DaoException;
import model.facade.IFacadeMetierClub;
import model.facade.exceptions.ClubException;
import org.apache.commons.collections4.IterableUtils;

import java.util.List;
import java.util.Optional;

public class FacadeMetierClubImpl implements IFacadeMetierClub {
    private final IClubDAO daoClub = DaoFactory.getClubDao();
    
    protected FacadeMetierClubImpl() {
    }
    
    @Override
    public Club saveClub(Club pClub) throws ClubException {
        try {
            return this.daoClub.save(pClub);
        } catch (DaoException e) {
            throw new ClubException(e);
        }
    }
    
    @Override
    public Optional<Club> findClub(Long pId) throws ClubException {
        try {
            return this.daoClub.find(pId);
        } catch (DaoException e) {
            throw new ClubException(e);
        }
    }
    
    @Override
    public List<Club> findAllClub() throws ClubException {
        try {
            return IterableUtils.toList(this.daoClub.findAll());
        } catch (DaoException e) {
            throw new ClubException(e);
        }
    }
    
    @Override
    public void deleteClub(Long pId) throws ClubException {
        try {
            this.daoClub.delete(pId);
        } catch (DaoException e) {
            throw new ClubException(e);
        }
    }
    
    @Override
    public Optional<Club> findClubByNomWithJudokas(String pName) throws ClubException {
        try {
            return this.daoClub.findClubByNomWithJudokas(pName);
        } catch (DaoException e) {
            throw new ClubException(e);
        }
    }
    
    @Override
    public List<Club> findAllClubByVille(Ville pVille) throws ClubException {
        try {
            return this.daoClub.findAllClubByVille(pVille);
        } catch (DaoException e) {
            throw new ClubException(e);
        }
    }
}
