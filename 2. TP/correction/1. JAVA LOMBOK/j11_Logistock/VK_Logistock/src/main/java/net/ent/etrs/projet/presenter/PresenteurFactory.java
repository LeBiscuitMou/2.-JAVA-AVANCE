package net.ent.etrs.projet.presenter;

import net.ent.etrs.projet.models.facades.FacadeMetier;
import net.ent.etrs.projet.models.references.ConstMetier;
import net.ent.etrs.projet.presenter.exceptions.PresenteurException;
import net.ent.etrs.projet.presenter.exceptions.PresenteurFactoryException;
import net.ent.etrs.projet.views.facades.FacadeView;

public final class PresenteurFactory {
    private PresenteurFactory() {
    }

    public static Presenteur fabriquerPresenteur(FacadeMetier fMet, FacadeView fVue) throws PresenteurFactoryException {
        try {
            return new Presenteur(fMet, fVue);
        } catch (PresenteurException e) {
            throw new PresenteurFactoryException(ConstMetier.ERROR_CREATION + Presenteur.class.getSimpleName(), e);
        }
    }
}
