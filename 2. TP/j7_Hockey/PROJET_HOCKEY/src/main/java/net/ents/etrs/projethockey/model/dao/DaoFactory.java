package net.ents.etrs.projethockey.model.dao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ents.etrs.projethockey.model.dao.impl.DaoChampionnatImpl;
import net.ents.etrs.projethockey.model.dao.impl.DaoEquipeImpl;
import net.ents.etrs.projethockey.model.dao.impl.DaoJoueurImpll;
import net.ents.etrs.projethockey.model.dao.impl.DaoMatchImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {


    @Getter
    private static final IDaoMatch daoMatch;
    @Getter
    private static final IDaoChampionnat daoChampionnat;
    @Getter
    private static final IDaoJoueur daoJoueur;
    @Getter
    private static final IDaoEquipe daoEquipe;

    static {
        daoMatch = new DaoMatchImpl();
        daoChampionnat = new DaoChampionnatImpl();
        daoJoueur = new DaoJoueurImpll();
        daoEquipe = new DaoEquipeImpl();

    }

}
