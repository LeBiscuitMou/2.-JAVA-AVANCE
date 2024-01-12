package net.ent.etrs.conso_elec_gaz.model.daos;

import net.ent.etrs.conso_elec_gaz.model.daos.exception.DaoException;
import net.ent.etrs.conso_elec_gaz.model.entities.Region;

public interface DaoRegion extends BaseDao<Region> {
    Region findByCode(Integer codeRegion) throws DaoException;
}
