package net.ent.etrs.keepFit.model.facade.impl;

import net.ent.etrs.keepFit.model.dao.IDaoAbonne;
import net.ent.etrs.keepFit.model.dao.exceptions.DaoException;
import net.ent.etrs.keepFit.model.dao.impl.DaoFactory;
import net.ent.etrs.keepFit.model.entities.Abonne;
import net.ent.etrs.keepFit.model.facade.exceptions.BusinessException;
import net.ent.etrs.keepFit.model.facade.IFacadeMetierAbonne;
import org.apache.commons.collections4.IterableUtils;

import java.util.HashSet;
import java.util.Set;

public class FacadeMetierAbonneImpl implements IFacadeMetierAbonne {
    private IDaoAbonne daoAbonne;

    public FacadeMetierAbonneImpl() {
        daoAbonne = DaoFactory.getDaoAbonne();
    }

    @Override
    public Abonne creerAbonne(Abonne entity) throws BusinessException {
        try {
            return daoAbonne.save(entity);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void supprimerAbonne(Abonne entity) throws BusinessException {
        try {
            daoAbonne.delete(entity.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Abonne> recupererToutesLesAbonne() throws BusinessException {
        try {
            return new HashSet<>(IterableUtils.toList(daoAbonne.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}