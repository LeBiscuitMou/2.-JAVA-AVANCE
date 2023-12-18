package net.ent.etrs.projet.start;

import net.ent.etrs.projet.models.facade.impl.FactoryFacadeMetier;
import net.ent.etrs.projet.presenter.Presenteur;
import net.ent.etrs.projet.presenter.PresenteurFactory;
import net.ent.etrs.projet.presenter.exception.PresenteurException;
import net.ent.etrs.projet.presenter.exception.PresenteurFactoryException;
import net.ent.etrs.projet.views.facade.impl.FactoryFacadeView;

public class Lanceur {
    public static void main(String[] args) {
        Presenteur p = null;
        try {
            p = PresenteurFactory.fabriquerPresenteur(FactoryFacadeMetier.fabriquerFacadeMetier(), FactoryFacadeView.fabriquerFacadeView());
            p.start();
        } catch (PresenteurFactoryException e) {
            throw new RuntimeException(e);
        }
    }
}
