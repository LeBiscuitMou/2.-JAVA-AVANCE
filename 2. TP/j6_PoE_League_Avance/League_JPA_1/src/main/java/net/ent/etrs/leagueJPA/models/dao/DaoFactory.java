package net.ent.etrs.leagueJPA.models.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.leagueJPA.models.dao.impl.ChallengeDaoImplJPA;
import net.ent.etrs.leagueJPA.models.dao.impl.LeagueDaoImplJPA;
import net.ent.etrs.leagueJPA.models.dao.impl.PersonnageDaoImplJPA;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    private static ChallengeDao daoChallenge;
    private static LeagueDao daoLeague;
    private static PersonnageDao daoPersonnage;

    public static ChallengeDao getDaoChallenge() {
        if (Objects.isNull(daoChallenge)) {
            daoChallenge = new ChallengeDaoImplJPA();
        }
        return daoChallenge;
    }

    public static LeagueDao getDaoLeague() {
        if (Objects.isNull(daoLeague)) {
            daoLeague = new LeagueDaoImplJPA();
        }
        return daoLeague;
    }

    public static PersonnageDao getDaoPersonnage() {
        if (Objects.isNull(daoPersonnage)) {
            daoPersonnage = new PersonnageDaoImplJPA();
        }
        return daoPersonnage;
    }
}
