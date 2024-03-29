package net.ent.etrs.squelette.model.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    @Getter
    private static final IDaoExemple daoExemple;

    static {
        daoExemple = new DaoExempleImpl();
    }
}
