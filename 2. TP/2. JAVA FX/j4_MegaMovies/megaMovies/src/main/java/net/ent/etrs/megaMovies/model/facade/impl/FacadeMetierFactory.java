package net.ent.etrs.megaMovies.model.facade.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megaMovies.model.facade.IFacadeMetierChargementFichier;
import net.ent.etrs.megaMovies.model.facade.IFacadeMetierFilm;
import net.ent.etrs.megaMovies.model.facade.IFacadeMetierRealisateur;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    public static IFacadeMetierChargementFichier fabriquerFacadeMetierChargementFichier() {
        return new FacadeMetierChargementFichierImpl();
    }

    public static IFacadeMetierFilm fabriquerFacadeMetierFilm() {
        return new FacadeMetierFilmImpl();
    }

    public static IFacadeMetierRealisateur fabriquerFacadeMetierRealisateur() {
        return new FacadeMetierRealisateurImpl();
    }
}
