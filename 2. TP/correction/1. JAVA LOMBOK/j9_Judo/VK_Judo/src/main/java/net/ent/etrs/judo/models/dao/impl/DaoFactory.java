package net.ent.etrs.judo.models.dao.impl;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.judo.models.dao.IDaoClub;
import net.ent.etrs.judo.models.dao.IDaoCompetition;
import net.ent.etrs.judo.models.dao.IDaoJudoka;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {

    @Getter
    private static final IDaoClub daoClub;
    @Getter
    private static final IDaoCompetition daoCompetition;
    @Getter
    private static final IDaoJudoka daoJudoka;

    static {
        daoClub = new DaoClubJpaImpl();
        daoCompetition = new DaoCompetitionJpaImpl();
        daoJudoka = new DaoJudokaJpaImpl();
    }




}
