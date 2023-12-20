package net.ent.etrs.heartStone.models.facades.impl;


import lombok.NoArgsConstructor;
import net.ent.etrs.heartStone.models.facades.FacadeMetier;

@NoArgsConstructor
public final class FactoryFacadeMetier {


    public static FacadeMetier fabriquerFacadeMetier() {
        return new FacadeMetierImpl();
    }
}
