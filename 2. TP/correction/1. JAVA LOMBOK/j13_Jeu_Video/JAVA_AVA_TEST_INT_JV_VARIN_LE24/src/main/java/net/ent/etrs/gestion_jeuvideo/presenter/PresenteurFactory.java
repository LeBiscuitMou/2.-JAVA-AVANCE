package net.ent.etrs.gestion_jeuvideo.presenter;

import net.ent.etrs.gestion_jeuvideo.models.facades.FacadeMetier;
import net.ent.etrs.gestion_jeuvideo.models.references.ConstMetier;
import net.ent.etrs.gestion_jeuvideo.presenter.exceptions.PresenteurException;
import net.ent.etrs.gestion_jeuvideo.presenter.exceptions.PresenteurFactoryException;
import net.ent.etrs.gestion_jeuvideo.views.facades.FacadeView;

public final class PresenteurFactory {
    private PresenteurFactory() {
    }

    public static Presenteur fabriquerPresenteur() throws PresenteurFactoryException {
        try {
            return new Presenteur();
        } catch (PresenteurException e) {
            throw new PresenteurFactoryException(ConstMetier.ERROR_CREATION + Presenteur.class.getSimpleName(), e);
        }
    }
}
