package net.ent.etrs.garage.model.facade;

import net.ent.etrs.garage.model.entities.Marque;
import net.ent.etrs.garage.model.facade.exceptions.BusinessException;

import java.util.Set;

public interface IFacadeMetierMarque {
    Marque creerMarque(Marque marque) throws BusinessException;

    void supprimerMarque(Marque marque) throws BusinessException;

    Set<Marque> recupererToutesLesMarques() throws BusinessException;
}