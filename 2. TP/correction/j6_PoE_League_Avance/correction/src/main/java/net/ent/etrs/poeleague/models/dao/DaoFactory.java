package net.ent.etrs.poeleague.models.dao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.poeleague.models.dao.impl.DaoChallengeImpl;
import net.ent.etrs.poeleague.models.dao.impl.DaoLeagueImpl;
import net.ent.etrs.poeleague.models.dao.impl.DaoPersonnageImpl;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {

    @Getter
    private static final DaoLeague daoLeague;
    @Getter
    private static final DaoChallenge daoChallenge;
    @Getter
    private static final DaoPersonnage daoPersonnage;

    static {
        daoLeague = new DaoLeagueImpl();
        daoChallenge = new DaoChallengeImpl();
        daoPersonnage = new DaoPersonnageImpl();
    }

}
