package net.ent.etrs.keepFit.model.facade.impl;

import net.ent.etrs.keepFit.model.dao.IDaoPrestation;
import net.ent.etrs.keepFit.model.dao.exceptions.DaoException;
import net.ent.etrs.keepFit.model.dao.impl.DaoFactory;
import net.ent.etrs.keepFit.model.entities.Prestation;
import net.ent.etrs.keepFit.model.facade.exceptions.BusinessException;
import net.ent.etrs.keepFit.model.facade.IFacadeMetierPrestation;
import org.apache.commons.collections4.IterableUtils;

import java.util.HashSet;
import java.util.Set;

public class FacadeMetierPrestationImpl implements IFacadeMetierPrestation {
    private IDaoPrestation daoPrestation;

    public FacadeMetierPrestationImpl() {
        daoPrestation = DaoFactory.getDaoPrestation();
    }

    @Override
    public Prestation creerPrestation(Prestation entity) throws BusinessException {
        try {
            return daoPrestation.save(entity);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void supprimerPrestation(Prestation entity) throws BusinessException {
        try {
            daoPrestation.delete(entity.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Prestation> recupererToutesLesPrestation() throws BusinessException {
        try {
            return new HashSet<>(IterableUtils.toList(daoPrestation.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}