package net.ent.etrs.hockey.model.facade.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.hockey.model.facade.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    public static IFacadeChampionnat fabriquerFacadeChampionnat() {
        return new FacadeChampionnatImpl();
    }

    public static IFacadeChargementFichier fabriquerFacadeChargementFichier() {
        return new FacadeChargementFichierImpl();
    }

    public static IFacadeEquipe fabriquerFacadeEquipe() {
        return new FacadeEquipeImpl();
    }

    public static IFacadeJoueur fabriquerFacadeJoueur() {
        return new FacadeJoueurImpl();
    }

    public static IFacadeMatch fabriquerFacadeMatch() {
        return new FacadeMatchImpl();
    }
}
