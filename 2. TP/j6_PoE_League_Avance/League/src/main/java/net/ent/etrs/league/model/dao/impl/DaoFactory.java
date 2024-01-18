package net.ent.etrs.league.model.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.league.model.dao.IDaoChallenge;
import net.ent.etrs.league.model.dao.IDaoLeague;
import net.ent.etrs.league.model.dao.IDaoPersonnage;
import net.ent.etrs.league.model.dao.impl.DaoPersonnageImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    @Getter
    private static final IDaoChallenge daoChallenge;

    static {
        daoChallenge = new DaoChallengeImpl();
    }

    @Getter
    private static final IDaoPersonnage daoPersonnage;

    static {
        daoPersonnage = new DaoPersonnageImpl();
    }

    @Getter
    private static final IDaoLeague daoLeague;

    static {
        daoLeague = new DaoLeagueImpl();
    }
}
