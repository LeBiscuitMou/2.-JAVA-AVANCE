package net.ent.etrs.geststage.model.facade.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.geststage.model.facade.IFacadeMetierChargementFichier;
import net.ent.etrs.geststage.model.facade.IFacadeMetierStage;
import net.ent.etrs.geststage.model.facade.IFacadeMetierStagiaire;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    public static IFacadeMetierChargementFichier fabriquerFacadeMetierChargementFichier() {
        return new FacadeMetierChargementFichierImpl();
    }

    public static IFacadeMetierStage fabriquerFacadeMetierStage(){
        return new FacadeMetierStageImpl();
    }

    public static IFacadeMetierStagiaire fabriquerFacadeMetierStagiaire(){
        return new FacadeMetierStagiaireImpl();
    }
}
