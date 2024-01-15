package net.ent.etrs.logistock.model.facade.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.logistock.model.facade.IFacadeArticle;
import net.ent.etrs.logistock.model.facade.IFacadeChargementFichier;
import net.ent.etrs.logistock.model.facade.IFacadeClient;
import net.ent.etrs.logistock.model.facade.IFacadeCommande;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    public static IFacadeArticle fabriquerFacadeArticle() {
        return new FacadeArticleImpl();
    }

    public static IFacadeChargementFichier fabriquerFacadeChargementFichier() {
        return new FacadeChargementFichierImpl();
    }

    public static IFacadeClient fabriquerFacadeClient() {
        return new FacadeClientImpl();
    }

    public static IFacadeCommande fabriquerFacadeCommande() {
        return new FacadeCommandeImpl();
    }
}
