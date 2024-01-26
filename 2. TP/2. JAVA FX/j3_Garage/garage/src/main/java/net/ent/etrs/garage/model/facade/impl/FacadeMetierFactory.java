package net.ent.etrs.garage.model.facade.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.garage.model.facade.IFacadeMetierChargementFichier;
import net.ent.etrs.garage.model.facade.IFacadeMetierMarque;
import net.ent.etrs.garage.model.facade.IFacadeMetierVoiture;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    public static IFacadeMetierChargementFichier fabriquerFacadeMetierChargementFichier() {
        return new FacadeMetierChargementFichierImpl();
    }

    public static IFacadeMetierMarque fabriquerFacadeMetierMarque() {
        return new FacadeMetierMarqueImpl();
    }

    public static IFacadeMetierVoiture fabriquerFacadeMetierVoiture() {
        return new FacadeMetierVoitureImpl();
    }
}
