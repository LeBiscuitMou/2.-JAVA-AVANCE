package net.ent.etrs.cartes.models.facade.impl;

import net.ent.etrs.cartes.models.facade.FacadeMetier;

public final class FactoryFacadeMetier {

    private FactoryFacadeMetier() {
    }

    public static FacadeMetier fabriquerFacadeMetier() {
        return new FacadeMetierImpl();
    }
}
