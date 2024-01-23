package net.ent.etrs.hockey.model.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.hockey.model.dao.IDaoChampionnat;
import net.ent.etrs.hockey.model.dao.IDaoEquipe;
import net.ent.etrs.hockey.model.dao.IDaoJoueur;
import net.ent.etrs.hockey.model.dao.IDaoMatch;
import net.ent.etrs.hockey.model.dao.impl.DaoChampionnatImpl;
import net.ent.etrs.hockey.model.dao.impl.DaoEquipeImpl;
import net.ent.etrs.hockey.model.dao.impl.DaoJoueurImpl;
import net.ent.etrs.hockey.model.dao.impl.DaoMatchImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    @Getter
    private static final IDaoEquipe daoEquipe;

    @Getter
    private static final IDaoMatch daoMatch;

    @Getter
    private static final IDaoJoueur daoJoueur;

    @Getter
    private static final IDaoChampionnat daoChampionnat;

    static {
        daoEquipe = new DaoEquipeImpl();
        daoMatch = new DaoMatchImpl();
        daoJoueur = new DaoJoueurImpl();
        daoChampionnat = new DaoChampionnatImpl();
    }
}
