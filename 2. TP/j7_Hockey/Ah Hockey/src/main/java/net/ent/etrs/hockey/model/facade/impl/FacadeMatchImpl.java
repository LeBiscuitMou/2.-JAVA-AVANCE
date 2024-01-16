package net.ent.etrs.hockey.model.facade.impl;

import net.ent.etrs.hockey.model.dao.IDaoMatch;
import net.ent.etrs.hockey.model.dao.impl.DaoFactory;
import net.ent.etrs.hockey.model.entities.Match;
import net.ent.etrs.hockey.model.exceptions.DaoException;
import net.ent.etrs.hockey.model.facade.IFacadeMatch;
import net.ent.etrs.hockey.model.facade.exceptions.BusinessException;

public class FacadeMatchImpl implements IFacadeMatch {
    IDaoMatch daoMatch;

    public FacadeMatchImpl() {
        daoMatch = DaoFactory.getDaoMatch();
    }

    @Override
    public void saveMatch(Match match) throws BusinessException {
        try {
            daoMatch.save(match);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteMatch(Long idMatch) throws BusinessException {
        try {
            daoMatch.delete(idMatch);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}