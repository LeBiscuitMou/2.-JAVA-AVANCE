package net.ent.etrs.projet.models.dao.impl;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.projet.models.dao.IDaoExemple;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {

    @Getter
    private static final IDaoExemple daoExemple;

    static {
        daoExemple = new ExempleDaoJpaImpl();
    }


}
