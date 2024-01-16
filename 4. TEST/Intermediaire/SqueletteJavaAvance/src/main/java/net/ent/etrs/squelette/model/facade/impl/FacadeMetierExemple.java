package net.ent.etrs.squelette.model.facade.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.squelette.model.dao.DaoFactory;
import net.ent.etrs.squelette.model.dao.IDaoExemple;
import net.ent.etrs.squelette.model.facade.IFacadeMetierExemple;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FacadeMetierExemple implements IFacadeMetierExemple {
    private final IDaoExemple daoExemple = DaoFactory.getDaoExemple();
}