package net.ent.etrs.judo.models.facades.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.judo.models.facades.FacadeMetier;
import net.ent.etrs.judo.models.facades.interfaces.FacadeChargementFichier;
import net.ent.etrs.judo.models.facades.interfaces.FacadeClub;
import net.ent.etrs.judo.models.facades.interfaces.FacadeCompetition;
import net.ent.etrs.judo.models.facades.interfaces.FacadeJudoka;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    public static FacadeMetier fabriquerFacadeMetier(){
        return new FacadeMetierImpl();
    }

    public static FacadeChargementFichier fabriquerFacadeChargementFichier(){
        return new FacadeChargementFichierImpl();
    }

    public static FacadeJudoka fabriquerFacadeJudoka(){
        return new FacadeJudokaImpl();
    }

    public static FacadeClub fabriquerFacadeClub(){
        return new FacadeClubImpl();
    }

    public static FacadeCompetition fabriquerFacadeCompetition(){
        return new FacadeCompetitionImpl();
    }
}
