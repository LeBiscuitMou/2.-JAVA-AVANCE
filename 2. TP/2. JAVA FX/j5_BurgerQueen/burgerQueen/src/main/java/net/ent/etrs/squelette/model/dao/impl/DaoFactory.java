package net.ent.etrs.squelette.model.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.squelette.model.dao.IDaoClient;
import net.ent.etrs.squelette.model.dao.IDaoCommande;
import net.ent.etrs.squelette.model.dao.IDaoProduit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    @Getter
    private static final IDaoClient daoClient;

    static {
        daoClient = new DaoClientImpl();
    }

    @Getter
    private static final IDaoCommande daoCommande;

    static {
        daoCommande = new DaoCommandeImpl();
    }

    @Getter
    private static final IDaoProduit daoProduit;

    static {
        daoProduit = new DaoProduitImpl();
    }
}
