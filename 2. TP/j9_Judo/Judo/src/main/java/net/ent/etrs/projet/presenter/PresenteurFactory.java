package net.ent.etrs.projet.presenter;

import net.ent.etrs.projet.models.references.ConstanteMetier;
import net.ent.etrs.projet.presenter.exceptions.PresenteurException;
import net.ent.etrs.projet.presenter.exceptions.PresenteurFactoryException;
import net.ent.etrs.projet.views.facades.FacadeView;

public final class PresenteurFactory {
    private PresenteurFactory() {
    }

    public static Presenteur fabriquerPresenteur(FacadeMetier fMet) throws PresenteurFactoryException {
        try {
            return new Presenteur(fMet);
        } catch (PresenteurException e) {
            throw new PresenteurFactoryException(ConstanteMetier.ERROR_CREATION + Presenteur.class.getSimpleName(), e);
        }
    }
}
