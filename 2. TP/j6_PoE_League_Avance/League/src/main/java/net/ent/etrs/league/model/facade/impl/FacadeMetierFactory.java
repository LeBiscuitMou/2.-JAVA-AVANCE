package net.ent.etrs.league.model.facade.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.league.model.facade.IFacadeMetierChargementFichier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    public static IFacadeMetierExemple fabriquerFacadeMetierExemple() {
        return new FacadeMetierLeagueImpl();
    }

    public static IFacadeMetierChargementFichier fabriquerFacadeMetierChargementFichier() {
        return new FacadeMetierChargementFichierImpl();
    }
}
