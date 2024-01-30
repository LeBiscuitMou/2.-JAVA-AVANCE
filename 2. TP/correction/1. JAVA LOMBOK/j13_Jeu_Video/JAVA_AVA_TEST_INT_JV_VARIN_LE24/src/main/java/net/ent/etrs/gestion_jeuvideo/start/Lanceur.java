package net.ent.etrs.gestion_jeuvideo.start;

import net.ent.etrs.gestion_jeuvideo.presenter.Presenteur;
import net.ent.etrs.gestion_jeuvideo.presenter.PresenteurFactory;

public class Lanceur {
    public static void main(String[] args) {
        try {
            Presenteur presenteur = PresenteurFactory.fabriquerPresenteur();
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
