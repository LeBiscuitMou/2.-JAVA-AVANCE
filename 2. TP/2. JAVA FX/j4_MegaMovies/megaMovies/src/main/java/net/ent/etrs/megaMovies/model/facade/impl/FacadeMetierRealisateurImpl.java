package net.ent.etrs.megaMovies.model.facade.impl;

import net.ent.etrs.megaMovies.model.dao.IDaoRealisateur;
import net.ent.etrs.megaMovies.model.dao.exceptions.DaoException;
import net.ent.etrs.megaMovies.model.dao.impl.DaoFactory;
import net.ent.etrs.megaMovies.model.entities.Realisateur;
import net.ent.etrs.megaMovies.model.facade.exceptions.BusinessException;
import net.ent.etrs.megaMovies.model.facade.IFacadeMetierRealisateur;
import org.apache.commons.collections4.IterableUtils;

import java.util.HashSet;
import java.util.Set;

public class FacadeMetierRealisateurImpl implements IFacadeMetierRealisateur {
    private IDaoRealisateur daoRealisateur;

    public FacadeMetierRealisateurImpl() {
        daoRealisateur = DaoFactory.getDaoRealisateur();
    }

    @Override
    public Realisateur creerRealisateur(Realisateur entity) throws BusinessException {
        try {
            return daoRealisateur.save(entity);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void supprimerRealisateur(Realisateur entity) throws BusinessException {
        try {
            daoRealisateur.delete(entity.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Realisateur> recupererTousLesRealisateur() throws BusinessException {
        try {
            return new HashSet<>(IterableUtils.toList(daoRealisateur.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}