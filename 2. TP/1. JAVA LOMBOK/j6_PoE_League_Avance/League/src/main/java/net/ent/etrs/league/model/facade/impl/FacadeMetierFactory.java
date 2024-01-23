package net.ent.etrs.league.model.facade.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.league.model.facade.IFacadeMetierChallenge;
import net.ent.etrs.league.model.facade.IFacadeMetierChargementFichier;
import net.ent.etrs.league.model.facade.IFacadeMetierLeague;
import net.ent.etrs.league.model.facade.IFacadeMetierPersonnage;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    public static IFacadeMetierPersonnage fabriquerFacadeMetierPersonnage() {
        return new FacadeMetierPersonnageImpl();
    }

    public static IFacadeMetierChallenge fabriquerFacadeMetierChallenge() {
        return new FacadeMetierChallengeImpl();
    }

    public static IFacadeMetierLeague fabriquerFacadeMetierLeague() {
        return new FacadeMetierLeagueImpl();
    }

    public static IFacadeMetierChargementFichier fabriquerFacadeMetierChargementFichier() {
        return new FacadeMetierChargementFichierImpl();
    }
}
