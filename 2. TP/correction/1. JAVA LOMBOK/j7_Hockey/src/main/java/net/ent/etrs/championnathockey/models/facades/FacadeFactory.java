package net.ent.etrs.championnathockey.models.facades;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.championnathockey.models.facades.impl.FacadeChampionnatImpl;
import net.ent.etrs.championnathockey.models.facades.impl.FacadeChargementFichierImpl;
import net.ent.etrs.championnathockey.models.facades.impl.FacadeEquipeImpl;
import net.ent.etrs.championnathockey.models.facades.impl.FacadeJoueurImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeFactory {

    public static FacadeChampionnat fabriquerFacadeChampionnat() {
        return new FacadeChampionnatImpl();
    }

    public static FacadeEquipe fabriquerFacadeEquipe() {
        return new FacadeEquipeImpl();
    }

    public static FacadeJoueur fabriquerFacadeJoueur() {
        return new FacadeJoueurImpl();
    }

    public static FacadeChargementFichier fabriquerFacadeChargementFichier() {
        return new FacadeChargementFichierImpl();
    }
}
