package net.ent.etrs.consoElecgaz.models.facades;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.consoElecgaz.models.facades.impl.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeFactory {

    public static IFacadeChargementFichier fabriquerFacadeChargementFichier() {
        return new FacadeChargementFichierImpl();
    }

    public static IFacadeConsommation fabriquerFacadeConsomation() {
        return new FacadeConsomationImpl();
    }

    public static IFacadeOperateur fabriquerFacadeOperateur() {
        return new FacadeOperateurImpl();
    }

    public static IFacadeRegion fabriquerFacadeRegion() {
        return new FacadeRegionImpl();
    }

    public static IFacadeActivitePrincipale fabriquerFacadeActivitePrincipale() {
        return new FacadeActivitePrincipaleImpl();
    }
}