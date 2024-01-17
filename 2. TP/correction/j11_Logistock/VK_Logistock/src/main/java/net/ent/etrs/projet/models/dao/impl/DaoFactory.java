package net.ent.etrs.projet.models.dao.impl;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.projet.models.dao.IDaoArticle;
import net.ent.etrs.projet.models.dao.IDaoClient;
import net.ent.etrs.projet.models.dao.IDaoCommande;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    
    @Getter
    private static final IDaoClient daoClient;
    
    static {
        daoClient = new ClientDaoJpaImpl();
    }
    
    @Getter
    private static final IDaoCommande daoCommande;
    
    static {
        daoCommande = new CommandeDaoJpaImpl();
    }
    
    @Getter
    private static final IDaoArticle daoArticle;
    
    static {
        daoArticle = new ArticleDaoJpaImpl();
    }


}
