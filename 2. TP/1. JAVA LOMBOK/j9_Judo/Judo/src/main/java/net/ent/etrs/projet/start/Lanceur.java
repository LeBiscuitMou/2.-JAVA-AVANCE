package net.ent.etrs.projet.start;

import net.ent.etrs.projet.models.facades.impl.FacadeFactory;
import net.ent.etrs.projet.presenter.Presenteur;
import net.ent.etrs.projet.presenter.PresenteurFactory;

public class Lanceur {
    public static void main(String[] args) {

        try {
            // facade métier
            FacadeMetier fMet = FacadeFactory.fabriquerFacadeMetier();
            // presenteur (vue, facade)
            Presenteur presenteur = PresenteurFactory.fabriquerPresenteur(fMet);
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
