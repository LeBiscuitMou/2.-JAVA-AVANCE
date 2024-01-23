package net.ent.etrs.conso_elec_gaz.model.daos;

import net.ent.etrs.conso_elec_gaz.model.daos.exception.DaoException;
import net.ent.etrs.conso_elec_gaz.model.entities.ActivitePrincipale;
import net.ent.etrs.conso_elec_gaz.model.entities.Region;

public interface DaoActivitePrincipale extends BaseDao<ActivitePrincipale> {
    ActivitePrincipale findByCodeNAF(Integer codeNAF) throws DaoException;
}
