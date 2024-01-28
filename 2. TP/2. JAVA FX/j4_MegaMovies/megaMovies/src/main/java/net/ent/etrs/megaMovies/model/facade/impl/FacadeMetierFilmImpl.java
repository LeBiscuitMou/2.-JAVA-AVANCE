package net.ent.etrs.megaMovies.model.facade.impl;

import net.ent.etrs.megaMovies.model.dao.IDaoFilm;
import net.ent.etrs.megaMovies.model.dao.exceptions.DaoException;
import net.ent.etrs.megaMovies.model.dao.impl.DaoFactory;
import net.ent.etrs.megaMovies.model.entities.Film;
import net.ent.etrs.megaMovies.model.facade.exceptions.BusinessException;
import net.ent.etrs.megaMovies.model.facade.IFacadeMetierFilm;
import org.apache.commons.collections4.IterableUtils;

import java.util.HashSet;
import java.util.Set;

public class FacadeMetierFilmImpl implements IFacadeMetierFilm {
    private IDaoFilm daoFilm;

    public FacadeMetierFilmImpl() {
        daoFilm = DaoFactory.getDaoFilm();
    }

    @Override
    public Film creerFilm(Film entity) throws BusinessException {
        try {
            return daoFilm.save(entity);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void supprimerFilm(Film entity) throws BusinessException {
        try {
            daoFilm.delete(entity.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Film> recupererTousLesFilm() throws BusinessException {
        try {
            return new HashSet<>(IterableUtils.toList(daoFilm.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}