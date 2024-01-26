package net.ent.etrs.projet.models.facades.impl;

import net.ent.etrs.projet.models.dao.IDaoSoigneur;
import net.ent.etrs.projet.models.dao.impl.DaoFactory;
import net.ent.etrs.projet.models.entities.Soigneur;
import net.ent.etrs.projet.models.facades.interfaces.IFacadeSoigneur;

public class FacadeSoigneurImpl extends GenericFacadeImpl<Soigneur> implements IFacadeSoigneur {
    private final IDaoSoigneur daoSoigneur;

    protected FacadeSoigneurImpl() {
        super(DaoFactory.getDaoSoigneur());
        daoSoigneur = DaoFactory.getDaoSoigneur();
    }
}