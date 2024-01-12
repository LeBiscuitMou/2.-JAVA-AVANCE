package net.ent.etrs.projet.models.dao;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.projet.models.dao.impl.DaoClubImpl;
import net.ent.etrs.projet.models.dao.impl.DaoCompetitionImpl;
import net.ent.etrs.projet.models.dao.impl.DaoJudokaImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {

    @Getter
    private static final DaoClub daoClub;

    @Getter
    private static final DaoJudoka daoJudoka;

    @Getter
    private static final DaoCompetition daoCompetition;

    static {
        daoClub = new DaoClubImpl();
        daoJudoka = new DaoJudokaImpl();
        daoCompetition = new DaoCompetitionImpl();
    }


}
