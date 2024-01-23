package model.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.dao.impl.ClubDAO;
import model.dao.impl.CompetitionDAO;
import model.dao.impl.JudokaDAO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {

    public static IClubDAO getClubDao(){ return new ClubDAO(); }
    public static ICompetitionDAO getCompetitionDao(){ return new CompetitionDAO(); }
    public static IJudokaDAO getJudokaDao(){ return new JudokaDAO(); }

}
