package net.ent.etrs.leagueJPA.models.facades.impl;


import lombok.NoArgsConstructor;
import net.ent.etrs.leagueJPA.models.facades.FacadeMetier;

@NoArgsConstructor
public final class FactoryFacadeMetier {


    public static FacadeMetier fabriquerFacadeMetier() {
        return new FacadeMetierImpl();
    }
}
