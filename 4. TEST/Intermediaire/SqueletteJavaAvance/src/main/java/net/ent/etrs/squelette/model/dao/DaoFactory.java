package net.ent.etrs.squelette.model.dao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.squelette.model.dao.impl.DaoExempleImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    @Getter
    private static final IDaoExemple daoExemple;

    static {
        daoExemple = new DaoExempleImpl();
    }
}
