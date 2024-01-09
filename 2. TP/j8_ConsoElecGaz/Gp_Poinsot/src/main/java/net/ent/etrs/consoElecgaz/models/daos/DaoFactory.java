package net.ent.etrs.consoElecgaz.models.daos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.consoElecgaz.models.daos.impl.DaoImplActivitePricipale;
import net.ent.etrs.consoElecgaz.models.daos.impl.DaoImplConsommation;
import net.ent.etrs.consoElecgaz.models.daos.impl.DaoImplOperateur;
import net.ent.etrs.consoElecgaz.models.daos.impl.DaoImplRegion;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {

    @Getter
    public static final DaoOperateur daoOperateur;

    @Getter
    private static final DaoActivitePrincipale daoActivitePrincipale;

    @Getter
    private static final DaoConsommation daoConsommation;

    @Getter
    private static final DaoRegion daoRegion;

    static {
        daoActivitePrincipale = new DaoImplActivitePricipale();
        daoConsommation = new DaoImplConsommation();
        daoRegion = new DaoImplRegion();
        daoOperateur = new DaoImplOperateur();

    }
}
