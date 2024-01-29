package net.ent.etrs.garage.model.facade.impl;

import net.ent.etrs.garage.model.dao.IDaoVoiture;
import net.ent.etrs.garage.model.dao.exceptions.DaoException;
import net.ent.etrs.garage.model.dao.impl.DaoFactory;
import net.ent.etrs.garage.model.entities.Voiture;
import net.ent.etrs.garage.model.facade.IFacadeMetierVoiture;
import net.ent.etrs.garage.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.HashSet;
import java.util.Set;

public class FacadeMetierVoitureImpl implements IFacadeMetierVoiture {
    private IDaoVoiture daoVoiture;

    public FacadeMetierVoitureImpl() {
        daoVoiture = DaoFactory.getDaoVoiture();
    }

    @Override
    public Voiture creerVoiture(Voiture voiture) throws BusinessException {
        try {
            return daoVoiture.save(voiture);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void supprimerVoiture(Voiture voiture) throws BusinessException {
        try {
            daoVoiture.delete(voiture.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Voiture> recupererToutesLesVoitures() throws BusinessException {
        try {
            return new HashSet<>(IterableUtils.toList(this.daoVoiture.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}