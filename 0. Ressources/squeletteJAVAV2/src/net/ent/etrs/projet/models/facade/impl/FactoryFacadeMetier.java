package net.ent.etrs.projet.models.facade.impl;

import net.ent.etrs.projet.models.facade.FacadeMetier;

public final class FactoryFacadeMetier {

    private FactoryFacadeMetier() {
    }

    public static FacadeMetier fabriquerFacadeMetier() {
        return new FacadeMetierImpl();
    }
}
