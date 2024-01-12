package model.facade.impl;

import model.facade.IFacadeMetierClub;
import model.facade.IFacadeMetierCompetition;
import model.facade.IFacadeMetierJudoka;

public final class FacadeMetierFactory {
    
    private FacadeMetierFactory() {
    }
    
    public static IFacadeMetierClub fabriquerFacadeMetierClub() {
        return new FacadeMetierClubImpl();
    }
    
    public static IFacadeMetierCompetition fabriquerFacadeMetierCompetition() {
        return new FacadeMetierCompetitionImpl();
    }
    
    public static IFacadeMetierJudoka fabriquerFacadeMetierJudoka() {
        return new FacadeMetierJudokaImpl();
    }
}
