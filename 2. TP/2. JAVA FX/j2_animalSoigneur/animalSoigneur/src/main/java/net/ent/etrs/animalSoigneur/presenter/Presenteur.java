package net.ent.etrs.animalSoigneur.presenter;

import net.ent.etrs.animalSoigneur.model.facade.IFacadeMetierChargementFichier;
import net.ent.etrs.animalSoigneur.model.facade.exceptions.BusinessException;
import net.ent.etrs.animalSoigneur.model.facade.impl.FacadeMetierFactory;
import net.ent.etrs.animalSoigneur.presenter.exceptions.PresenteurException;

public class Presenteur {
    IFacadeMetierChargementFichier facadeMetierChargementFichier;

    public Presenteur() throws PresenteurException {
//        facadeMetierChargementFichier = FacadeMetierFactory.fabriquerFacadeMetierChargementFichier();
//
//        try {
//            facadeMetierChargementFichier.initialisation();
//        } catch (BusinessException e) {
//            throw new PresenteurException(e.getMessage(), e);
//        }
    }
}
