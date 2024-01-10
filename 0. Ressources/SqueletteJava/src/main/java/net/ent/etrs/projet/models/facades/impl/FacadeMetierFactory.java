package net.ent.etrs.projet.models.facades.impl;

import net.ent.etrs.projet.models.facades.FacadeMetier;

public final class FacadeMetierFactory {
    private FacadeMetierFactory() {

    }

    public static FacadeMetier fabriquerFacadeMetier(){
        return new FacadeMetierImpl();
     }
}
