package net.ent.etrs.squelette.model.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.squelette.model.dao.IDaoAnimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    @Getter
    private static final IDaoAnimal daoAnimal;

    static {
        daoAnimal = new DaoAnimalImpl();
    }
}
