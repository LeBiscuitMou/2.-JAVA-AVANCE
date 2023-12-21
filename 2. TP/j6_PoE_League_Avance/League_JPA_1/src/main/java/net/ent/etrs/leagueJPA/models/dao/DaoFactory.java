package net.ent.etrs.leagueJPA.models.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.heartstoneJPA.models.dao.impl.CarteDaoImplJPA;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    private static CarteDao daoCarte;

    public static CarteDao getDaoCarte(){
        if (Objects.isNull(daoCarte)){
            daoCarte = new CarteDaoImplJPA();
        }
        return daoCarte;
    }
}
