package net.ent.etrs.garage.model.facade.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.garage.model.facade.IFacadeMetierChargementFichier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    public static IFacadeMetierExemple fabriquerFacadeMetierExemple() {
        return new FacadeMetierExempleImpl();
    }

    public static IFacadeMetierChargementFichier fabriquerFacadeMetierChargementFichier() {
        return new FacadeMetierChargementFichierImpl();
    }
}
