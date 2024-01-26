package net.ent.etrs.projetjavamaier.models.facade.impl;

import net.ent.etrs.projetjavamaier.models.daos.DaoFactory;
import net.ent.etrs.projetjavamaier.models.daos.FabriquantDao;
import net.ent.etrs.projetjavamaier.models.entities.Fabriquant;
import net.ent.etrs.projetjavamaier.models.facade.FabriquantFacade;
import net.ent.etrs.projetjavamaier.models.facade.base.AbstractFacade;


public class FacadeFabriquantImpl extends AbstractFacade<Fabriquant> implements FabriquantFacade {

    FabriquantDao daoFabriquant;

    public FacadeFabriquantImpl() {
        this.daoFabriquant = DaoFactory.getFabriquantDao();
    }
}