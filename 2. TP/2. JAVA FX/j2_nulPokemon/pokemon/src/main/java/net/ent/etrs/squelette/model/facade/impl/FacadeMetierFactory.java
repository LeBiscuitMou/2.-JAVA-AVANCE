package net.ent.etrs.squelette.model.facade.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.squelette.model.facade.IFacadeMetierChargementFichier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    public static IFacadeMetierChargementFichier fabriquerFacadeMetierChargementFichier() {
        return new FacadeMetierChargementFichierImpl();
    }
}
