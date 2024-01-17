package net.ent.etrs.projet.models.facades.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.projet.models.facades.FacadeMetier;
import net.ent.etrs.projet.models.facades.interfaces.FacadeChargementFichier;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    public static FacadeMetier fabriquerFacadeMetier(){
        return new FacadeMetierImpl();
    }

    public static FacadeChargementFichier fabriquerFacadeChargementFichier(){
        return new FacadeChargementFichierImpl();
    }
}
