package net.ent.etrs.keepFit.model.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.keepFit.model.dao.IDaoAbonne;
import net.ent.etrs.keepFit.model.dao.IDaoPrestation;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    @Getter
    private static final IDaoAbonne daoAbonne;

    static {
        daoAbonne = new DaoAbonneImpl();
    }

    @Getter
    private static final IDaoPrestation daoPrestation;

    static {
        daoPrestation = new DaoPrestationImpl();
    }
}
