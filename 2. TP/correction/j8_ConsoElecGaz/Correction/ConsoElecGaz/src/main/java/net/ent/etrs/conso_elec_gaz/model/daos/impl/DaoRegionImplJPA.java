package net.ent.etrs.conso_elec_gaz.model.daos.impl;

import net.ent.etrs.conso_elec_gaz.model.daos.DaoRegion;
import net.ent.etrs.conso_elec_gaz.model.daos.exception.DaoException;
import net.ent.etrs.conso_elec_gaz.model.entities.Operateur;
import net.ent.etrs.conso_elec_gaz.model.entities.Region;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class DaoRegionImplJPA extends AbstractJpaDao<Region> implements DaoRegion {
    @Override
    public Region findByCode(Integer codeRegion) throws DaoException {
        try {
            TypedQuery<Region> tp = this.em.createQuery(
                    "SELECT r FROM Region r " +
                            "WHERE r.codeRegion = :codeRegion ", Region.class);
            tp.setParameter("codeRegion", codeRegion);
            return tp.getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
        catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
