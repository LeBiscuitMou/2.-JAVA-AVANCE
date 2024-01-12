package net.ent.etrs.judo.presenter;

import net.ent.etrs.judo.models.references.ConstMetier;
import net.ent.etrs.judo.presenter.exceptions.PresenteurException;
import net.ent.etrs.judo.presenter.exceptions.PresenteurFactoryException;
import net.ent.etrs.judo.views.facades.FacadeView;

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
