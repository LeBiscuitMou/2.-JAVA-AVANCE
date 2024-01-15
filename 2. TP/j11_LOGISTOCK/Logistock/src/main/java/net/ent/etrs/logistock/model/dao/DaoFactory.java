package net.ent.etrs.logistock.model.dao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.logistock.model.dao.impl.DaoArticleImpl;
import net.ent.etrs.logistock.model.dao.impl.DaoClientImpl;
import net.ent.etrs.logistock.model.dao.impl.DaoCommandeImpl;

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
