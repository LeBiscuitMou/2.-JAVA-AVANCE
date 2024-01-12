package net.ent.etrs.judo.start;

import net.ent.etrs.judo.models.facades.impl.FacadeMetierFactory;
import net.ent.etrs.judo.presenter.Presenteur;
import net.ent.etrs.judo.presenter.PresenteurFactory;
import net.ent.etrs.judo.views.facades.FacadeView;
import net.ent.etrs.judo.views.facades.impl.FacadeViewFactory;

public class Lanceur {
    public static void main(String[] args) {
        try {
            // facade vue
            FacadeView fVue = FacadeViewFactory.fabriquerFacadeVue();
            // facade métier
            FacadeMetier fMet = FacadeMetierFactory.fabriquerFacadeMetier();
            // presenteur (vue, facade)
            Presenteur presenteur = PresenteurFactory.fabriquerPresenteur(fMet, fVue);
            // exec presenteur
            presenteur.exec();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /* ******************************** VARIABLES ******************************** */

    /* ******************************* CONSTRUCTOR ******************************* */

    /* ********************************* GETTERS ********************************* */

    /* ********************************* SETTERS ********************************* */

    /* ******************************** FONCTIONS ******************************** */

    /* **************************** PRIVATE FUNCTIONS **************************** */
}
