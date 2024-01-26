package net.ent.etrs.projet.models.dao.impl;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.projet.models.dao.IDaoAnimal;
import net.ent.etrs.projet.models.dao.IDaoSoigneur;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    @Getter
    private static final IDaoAnimal daoAnimal;

    @Getter
    private static final IDaoSoigneur daoSoigneur;

    static {
        daoAnimal = new AnimalDaoJpaImpl();
        daoSoigneur = new SoigneurDaoJpaImpl();
    }

}
