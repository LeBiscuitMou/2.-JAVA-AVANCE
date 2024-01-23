package net.ent.etrs.projet.models.facades.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.projet.models.facades.interfaces.IFacadeChargementFichier;
import net.ent.etrs.projet.models.facades.interfaces.*;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    public static IFacadeClient fabriquerFacadeClient(){
        return new FacadeClientImpl();
    }
    public static IFacadeCommande fabriquerFacadeCommande(){
        return new FacadeCommandeImpl();
    }
    public static IFacadeArticle fabriquerFacadeArticle(){
        return new FacadeArticleImpl();
    }
    public static IFacadeChargementFichier fabriquerFacadeChargementArticle(){
        return new FacadeChargementArticleImpl();
    }
    public static IFacadeChargementFichier fabriquerFacadeChargementClient(){
        return new FacadeChargementClientImpl();
    }
    public static IFacadeChargementFichier fabriquerFacadeChargementCommande(){
        return new FacadeChargementCommandeImpl();
    }

}
