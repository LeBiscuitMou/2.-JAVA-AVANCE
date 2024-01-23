package net.ent.etrs.consoElecgaz.models.facades;

import net.ent.etrs.consoElecgaz.models.entities.ActivitePrincipale;
import net.ent.etrs.consoElecgaz.models.facades.exceptions.BusinessException;

public interface IFacadeActivitePrincipale {
    ActivitePrincipale sauvegarderActivitePrincipale(ActivitePrincipale activitePrincipale) throws BusinessException;
}
