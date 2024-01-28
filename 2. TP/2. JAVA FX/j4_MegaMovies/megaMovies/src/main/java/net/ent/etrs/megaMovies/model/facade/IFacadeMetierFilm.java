package net.ent.etrs.megaMovies.model.facade;

import net.ent.etrs.megaMovies.model.entities.Film;
import net.ent.etrs.megaMovies.model.facade.exceptions.BusinessException;

import java.util.Set;

public interface IFacadeMetierFilm {
    Film creerFilm(Film entity) throws BusinessException;

    void supprimerFilm(Film entity) throws BusinessException;

    Set<Film> recupererTousLesFilm() throws BusinessException;
}