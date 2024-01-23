package net.ent.etrs.conso_elec_gaz.model.daos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.conso_elec_gaz.model.daos.impl.DaoActivitePrincipaleImplJPA;
import net.ent.etrs.conso_elec_gaz.model.daos.impl.DaoConsomationImplJPA;
import net.ent.etrs.conso_elec_gaz.model.daos.impl.DaoOperateurImplJPA;
import net.ent.etrs.conso_elec_gaz.model.daos.impl.DaoRegionImplJPA;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {

@Getter
private static final DaoConsomation daoConsomation;
@Getter
private static final DaoOperateur daoOperateur;
@Getter
private static final DaoRegion daoRegion;
@Getter
private static final DaoActivitePrincipale daoActivitePrincipale;

static {
    daoConsomation = new DaoConsomationImplJPA();
    daoOperateur = new DaoOperateurImplJPA();
    daoRegion = new DaoRegionImplJPA();
    daoActivitePrincipale = new DaoActivitePrincipaleImplJPA();
}

}
