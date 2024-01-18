package net.ent.etrs.squelette.presenter;

import net.ent.etrs.squelette.model.facade.IFacadeMetierChargementFichier;
import net.ent.etrs.squelette.model.facade.exceptions.BusinessException;
import net.ent.etrs.squelette.model.facade.impl.FacadeMetierFactory;
import net.ent.etrs.squelette.presenter.exceptions.PresenteurException;

public class Presenteur {
    IFacadeMetierChargementFichier facadeMetierChargementFichier;

    public Presenteur() throws PresenteurException {
        facadeMetierChargementFichier = FacadeMetierFactory.fabriquerFacadeMetierChargementFichier();

        try {
            facadeMetierChargementFichier.initialisation();
        } catch (BusinessException e) {
            throw new PresenteurException(e.getMessage(), e);
        }
    }
}
