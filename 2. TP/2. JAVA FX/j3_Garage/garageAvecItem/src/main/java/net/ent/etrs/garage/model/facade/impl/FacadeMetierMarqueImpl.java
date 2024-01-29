package net.ent.etrs.garage.model.facade.impl;

import net.ent.etrs.garage.model.dao.IDaoMarque;
import net.ent.etrs.garage.model.dao.exceptions.DaoException;
import net.ent.etrs.garage.model.dao.impl.DaoFactory;
import net.ent.etrs.garage.model.entities.Marque;
import net.ent.etrs.garage.model.facade.IFacadeMetierMarque;
import net.ent.etrs.garage.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.HashSet;
import java.util.Set;

public class FacadeMetierMarqueImpl implements IFacadeMetierMarque {
    private IDaoMarque daoMarque;

    public FacadeMetierMarqueImpl() {
        daoMarque = DaoFactory.getDaoMarque();
    }

    @Override
    public Marque creerMarque(Marque marque) throws BusinessException {
        try {
            return daoMarque.save(marque);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void supprimerMarque(Marque marque) throws BusinessException {
        try {
            daoMarque.delete(marque.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Marque> recupererToutesLesMarques() throws BusinessException {
        try {
            return new HashSet<>(IterableUtils.toList(daoMarque.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}