package net.ent.etrs.animalSoigneur.model.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.animalSoigneur.model.dao.IDaoAnimal;
import net.ent.etrs.animalSoigneur.model.dao.IDaoSoigneur;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    @Getter
    private static final IDaoAnimal daoAnimal;

    static {
        daoAnimal = new DaoAnimalImpl();
    }

    @Getter
    private static final IDaoSoigneur daoSoigneur;

    static {
        daoSoigneur = new DaoSoigneurImpl();
    }
}
