package net.ent.etrs.projet.models.facades.impl;

import net.ent.etrs.projet.models.dao.IDaoAnimal;
import net.ent.etrs.projet.models.dao.impl.DaoFactory;
import net.ent.etrs.projet.models.entities.Animal;
import net.ent.etrs.projet.models.facades.interfaces.IFacadeAnimal;

public class FacadeAnimalImpl extends GenericFacadeImpl<Animal> implements IFacadeAnimal {
    private final IDaoAnimal daoAnimal;

    protected FacadeAnimalImpl() {
        super(DaoFactory.getDaoAnimal());
        daoAnimal = DaoFactory.getDaoAnimal();
    }
}