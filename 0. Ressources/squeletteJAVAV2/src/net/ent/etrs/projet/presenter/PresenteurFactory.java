package net.ent.etrs.projet.presenter;

import net.ent.etrs.projet.models.facade.FacadeMetier;
import net.ent.etrs.projet.presenter.exception.PresenteurException;
import net.ent.etrs.projet.presenter.exception.PresenteurFactoryException;
import net.ent.etrs.projet.presenter.references.ConstantesPresenteur;
import net.ent.etrs.projet.views.facade.FacadeView;

public final class PresenteurFactory {
    private PresenteurFactory() {
    }

    public static Presenteur fabriquerPresenteur(FacadeMetier facadeMetier, FacadeView facadeView) throws PresenteurFactoryException {
        try {
            return new Presenteur(facadeMetier, facadeView);
        } catch (PresenteurException e) {
            throw new PresenteurFactoryException(ConstantesPresenteur.PRESENTEUR_FACTORY_EXCEPTION, e);
        }
    }
}