package net.ent.etrs.squelette.model.facade.impl;

import net.ent.etrs.squelette.model.dao.DaoFactory;
import net.ent.etrs.squelette.model.dao.IDaoExemple;
import net.ent.etrs.squelette.model.facade.IFacadeMetierExemple;

public class FacadeMetierExempleImpl implements IFacadeMetierExemple {
    private IDaoExemple daoExemple;

    protected FacadeMetierExempleImpl() {
        daoExemple = DaoFactory.getDaoExemple();
    }
}