package net.ent.etrs.hockey.models.dao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.hockey.models.dao.impl.DaoChampionnatImpl;
import net.ent.etrs.hockey.models.dao.impl.DaoEquipeImpl;
import net.ent.etrs.hockey.models.dao.impl.DaoJoueurImpl;
import net.ent.etrs.hockey.models.dao.impl.DaoMatchImpl;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {

    @Getter
    private static final DaoChampionnat daoChampionnat;
    @Getter
    private static final DaoEquipe daoEquipe;
    @Getter
    private static final DaoJoueur daoJoueur;
    @Getter
    private static final DaoMatch daoMatch;

    static {
        daoChampionnat = new DaoChampionnatImpl();
        daoEquipe = new DaoEquipeImpl();
        daoJoueur = new DaoJoueurImpl();
        daoMatch = new DaoMatchImpl();
    }

}
