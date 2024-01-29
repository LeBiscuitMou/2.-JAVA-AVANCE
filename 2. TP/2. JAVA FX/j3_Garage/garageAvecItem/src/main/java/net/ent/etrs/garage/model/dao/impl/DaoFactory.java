package net.ent.etrs.garage.model.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.garage.model.dao.IDaoMarque;
import net.ent.etrs.garage.model.dao.IDaoVoiture;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    @Getter
    private static final IDaoMarque daoMarque;

    static {
        daoMarque = new DaoMarqueImpl();
    }

    @Getter
    private static final IDaoVoiture daoVoiture;

    static {
        daoVoiture = new DaoVoitureImpl();
    }
}
