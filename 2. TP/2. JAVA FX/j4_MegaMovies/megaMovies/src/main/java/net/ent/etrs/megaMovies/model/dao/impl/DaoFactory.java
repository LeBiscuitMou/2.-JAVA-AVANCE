package net.ent.etrs.megaMovies.model.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.megaMovies.model.dao.IDaoFilm;
import net.ent.etrs.megaMovies.model.dao.IDaoRealisateur;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    @Getter
    private static final IDaoFilm daoFilm;

    static {
        daoFilm = new DaoFilmImpl();
    }

    @Getter
    private static final IDaoRealisateur daoRealisateur;

    static {
        daoRealisateur = new DaoRealisateurImpl();
    }
}
