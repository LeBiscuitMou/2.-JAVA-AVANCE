package net.ent.etrs.league.presenter;

import net.ent.etrs.league.model.facade.IFacadeMetierChargementFichier;
import net.ent.etrs.league.model.facade.exceptions.BusinessException;
import net.ent.etrs.league.model.facade.impl.FacadeMetierFactory;
import net.ent.etrs.league.presenter.exceptions.PresenteurException;

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
