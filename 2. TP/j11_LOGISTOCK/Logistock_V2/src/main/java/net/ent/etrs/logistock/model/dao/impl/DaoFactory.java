package net.ent.etrs.logistock.model.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.logistock.model.dao.IDaoArticle;
import net.ent.etrs.logistock.model.dao.IDaoClient;
import net.ent.etrs.logistock.model.dao.IDaoCommande;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    @Getter
    private static final IDaoArticle daoArticle;

    @Getter
    private static final IDaoClient daoClient;

    @Getter
    private static final IDaoCommande daoCommande;

    static {
        daoArticle = new DaoArticleImpl();
        daoClient = new DaoClientImpl();
        daoCommande = new DaoCommandeImpl();
    }
}
