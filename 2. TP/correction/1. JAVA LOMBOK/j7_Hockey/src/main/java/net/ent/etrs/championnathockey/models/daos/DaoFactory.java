package net.ent.etrs.championnathockey.models.daos;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.championnathockey.models.daos.impl.DaoChampionnatImpl;
import net.ent.etrs.championnathockey.models.daos.impl.DaoEquipeImpl;
import net.ent.etrs.championnathockey.models.daos.impl.DaoJoueurImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {

private static final DaoJoueur daoJoueur;
private static final DaoEquipe daoEquipe;
private static final DaoChampionnat daoChampionnat;

static {
    daoJoueur = new DaoJoueurImpl();
    daoEquipe = new DaoEquipeImpl();
    daoChampionnat = new DaoChampionnatImpl();
}

    public static DaoJoueur getDaoJoueur() {
        return daoJoueur;
    }
    public static DaoEquipe getDaoEquipe() {
        return daoEquipe;
    }
    public static DaoChampionnat getDaoChampionnat() {
        return daoChampionnat;
    }
}
