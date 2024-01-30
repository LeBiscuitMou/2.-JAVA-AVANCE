package net.ent.etrs.geststage.model.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.geststage.model.dao.IDaoStage;
import net.ent.etrs.geststage.model.dao.IDaoStagiaire;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    @Getter
    private static final IDaoStage daoStage;

    static {
        daoStage = new DaoStageImpl();
    }

    @Getter
    private static final IDaoStagiaire daoStagiaire;

    static {
        daoStagiaire = new DaoStagiaireImpl();
    }
}
