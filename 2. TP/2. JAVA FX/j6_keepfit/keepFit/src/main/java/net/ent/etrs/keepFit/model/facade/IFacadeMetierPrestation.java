package net.ent.etrs.keepFit.model.facade;

import net.ent.etrs.keepFit.model.entities.Prestation;
import net.ent.etrs.keepFit.model.facade.exceptions.BusinessException;

import java.util.Set;

public interface IFacadeMetierPrestation {
    Prestation creerPrestation(Prestation entity) throws BusinessException;

    void supprimerPrestation(Prestation entity) throws BusinessException;

    Set<Prestation> recupererToutesLesPrestation() throws BusinessException;
}