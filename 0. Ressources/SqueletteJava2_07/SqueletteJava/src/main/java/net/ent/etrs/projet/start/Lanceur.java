package net.ent.etrs.projet.start;

import net.ent.etrs.projet.models.facades.FacadeMetier;
import net.ent.etrs.projet.models.facades.impl.FacadeMetierFactory;
import net.ent.etrs.projet.presenter.Presenteur;
import net.ent.etrs.projet.presenter.PresenteurFactory;
import net.ent.etrs.projet.views.facades.FacadeView;
import net.ent.etrs.projet.views.facades.impl.FacadeViewFactory;

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
