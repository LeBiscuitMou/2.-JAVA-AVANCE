package net.ent.etrs.logistock.model.facade.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.logistock.model.facade.IFacadeMetierChargementFichier;
import net.ent.etrs.logistock.model.facade.IFacadeMetierArticle;
import net.ent.etrs.logistock.model.facade.IFacadeMetierClient;
import net.ent.etrs.logistock.model.facade.IFacadeMetierCommande;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    public static IFacadeMetierArticle fabriquerFacadeMetierArticle() {
        return new FacadeMetierArticleImpl();
    }

    public static IFacadeMetierChargementFichier fabriquerFacadeMetierChargementFichier() {
        return new FacadeMetierChargementFichierImpl();
    }

    public static IFacadeMetierClient fabriquerFacadeMetierClient() {
        return new FacadeMetierClientImpl();
    }

    public static IFacadeMetierCommande fabriquerFacadeMetierCommande() {
        return new FacadeMetierCommandeImpl();
    }
}
