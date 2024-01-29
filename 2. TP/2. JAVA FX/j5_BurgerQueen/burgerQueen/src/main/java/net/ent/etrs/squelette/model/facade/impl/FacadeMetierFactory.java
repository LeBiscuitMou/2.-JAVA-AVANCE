package net.ent.etrs.squelette.model.facade.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.squelette.model.facade.IFacadeMetierChargementFichier;
import net.ent.etrs.squelette.model.facade.IFacadeMetierClient;
import net.ent.etrs.squelette.model.facade.IFacadeMetierCommande;
import net.ent.etrs.squelette.model.facade.IFacadeMetierProduit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    public static IFacadeMetierChargementFichier fabriquerFacadeMetierChargementFichier() {
        return new FacadeMetierChargementFichierImpl();
    }

    public static IFacadeMetierProduit fabriquerFacadeMetierProduit(){
        return new FacadeMetierProduitImpl();
    }

    public static IFacadeMetierClient fabriquerFacadeMetierClient(){
        return new FacadeMetierClientImpl();
    }

    public static IFacadeMetierCommande fabriquerFacadeMetierCommande(){
        return new FacadeMetierCommandeImpl();
    }
}
