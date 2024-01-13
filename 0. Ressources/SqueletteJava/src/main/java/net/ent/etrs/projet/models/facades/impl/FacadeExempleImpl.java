package net.ent.etrs.projet.models.facades.impl;

import net.ent.etrs.projet.models.dao.IDaoExemple;
import net.ent.etrs.projet.models.dao.impl.DaoFactory;
import net.ent.etrs.projet.models.entities.Exemple;
import net.ent.etrs.projet.models.facades.interfaces.FacadeExemple;

public class FacadeExempleImpl extends GenericFacadeImpl<Exemple> implements FacadeExemple {
    protected IDaoExemple daoExemple;

    protected FacadeExempleImpl() {
        super(DaoFactory.getDaoExemple());
        daoExemple = DaoFactory.getDaoExemple();
    }
}
