package net.ent.etrs.conso_elec_gaz.model.daos.impl;

import net.ent.etrs.conso_elec_gaz.model.daos.DaoActivitePrincipale;
import net.ent.etrs.conso_elec_gaz.model.daos.exception.DaoException;
import net.ent.etrs.conso_elec_gaz.model.entities.ActivitePrincipale;
import net.ent.etrs.conso_elec_gaz.model.entities.Operateur;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

public class DaoActivitePrincipaleImplJPA extends AbstractJpaDao<ActivitePrincipale> implements DaoActivitePrincipale {
    @Override
    public ActivitePrincipale findByCodeNAF(Integer codeNAF) throws DaoException {
        try {
            TypedQuery<ActivitePrincipale> tp = this.em.createQuery(
                    "SELECT a FROM ActivitePrincipale a " +
                            " WHERE a.codeNAF = :codeNAF", ActivitePrincipale.class);
            tp.setParameter("codeNAF", codeNAF);
            return tp.getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
