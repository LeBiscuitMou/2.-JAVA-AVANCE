package net.ent.etrs.consoElecgaz.models.facades.impl;

import net.ent.etrs.consoElecgaz.models.daos.DaoActivitePrincipale;
import net.ent.etrs.consoElecgaz.models.daos.DaoFactory;
import net.ent.etrs.consoElecgaz.models.daos.exception.DaoException;
import net.ent.etrs.consoElecgaz.models.entities.ActivitePrincipale;
import net.ent.etrs.consoElecgaz.models.facades.IFacadeActivitePrincipale;
import net.ent.etrs.consoElecgaz.models.facades.exceptions.BusinessException;

public class FacadeActivitePrincipaleImpl implements IFacadeActivitePrincipale {
    DaoActivitePrincipale daoActivitePrincipale;

    public FacadeActivitePrincipaleImpl() {
        this.daoActivitePrincipale = DaoFactory.getDaoActivitePrincipale();
    }

    @Override
    public ActivitePrincipale sauvegarderActivitePrincipale(ActivitePrincipale activitePrincipale) throws BusinessException {
        try {
            return daoActivitePrincipale.save(activitePrincipale);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }
}