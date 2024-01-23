package net.ent.etrs.judo.presenter;

import net.ent.etrs.judo.models.facades.FacadeMetier;
import net.ent.etrs.judo.models.references.*;
import net.ent.etrs.judo.presenter.exceptions.PresenteurException;
import net.ent.etrs.judo.views.facades.FacadeView;
import net.ent.etrs.judo.views.references.ConstView;

import java.util.Objects;

public class Presenteur {
    /* ******************************** VARIABLES ******************************** */
    private FacadeMetier fMet;
    private FacadeView fVue;
    private boolean initialiser = false;

    /* ******************************* CONSTRUCTOR ******************************* */
    protected Presenteur(FacadeMetier fMet, FacadeView fVue) throws PresenteurException {
        this.setFMet(fMet);
        this.setFVue(fVue);
    }
    /* ********************************* GETTERS ********************************* */

    /* ********************************* SETTERS ********************************* */
    private void setFMet(FacadeMetier fMet) throws PresenteurException {
        if(Objects.isNull(fMet)){
            throw new PresenteurException(FacadeMetier.class.getSimpleName() + ConstMetier.ERROR_NULL);
        }
        this.fMet = fMet;
    }

    public void setFVue(FacadeView fVue) throws PresenteurException {
        if(Objects.isNull(fVue)){
            throw new PresenteurException(FacadeView.class.getSimpleName() + ConstMetier.ERROR_NULL);
        }
        this.fVue = fVue;
    }

    /* ******************************** FONCTIONS ******************************** */
    public void exec(){
        mainProgramme();
    }



    /* **************************** PRIVATE FUNCTIONS **************************** */

    private void mainProgramme() {
        boolean quitter = false;
        do {
            int choixMenu = fVue.lectureChoix(fVue.afficherMenuPrincipal().size());
            switch (choixMenu){
                case 1 -> initialiser();
                case 2 -> sousProgramme2();
                case 3 -> sousProgramme3();
                case 4 -> sousProgramme4();
                case 5 -> sousProgramme5();
                case 6 -> sousProgramme6();
                case 7 -> sousProgramme7();
                case 8 -> sousProgramme8();
                case 9 -> sousProgramme9();
                case 0 -> quitter = true;
                default -> fVue.afficherMessage(ConstView.MESSAGE_ERREUR_MAIN_SWITCH);
            }
        }while (!quitter);

        fVue.afficherMessage(ConstView.MESSAGE_SORTIE_PROGRAMME);
    }

    private void sousProgramme2() {

    }

    private void sousProgramme3() {

    }

    private void sousProgramme4() {

    }

    private void sousProgramme5() {

    }

    private void sousProgramme6() {

    }

    private void sousProgramme7() {

    }

    private void sousProgramme8() {

    }

    private void sousProgramme9() {

    }

    /**
     * Methode fournie
     */
    private void initialiser() {
        if(!initialiser){
            initialiser = true;


        }
        else {
            fVue.afficherMessage(ConstView.INITIALISATION_ALREADY_DONE);
        }


    }



    /**
     * Permet d'afficher tous les messages liés à une erreur.
     *
     * @param error l'exception dont il faut afficher les messages
     */
    private void afficherToutesErreur(Throwable error) {
        if(Objects.nonNull(error.getCause())){
            afficherToutesErreur(error.getCause());
        }
        fVue.afficherMessageErreur(error.getMessage());
    }

}
