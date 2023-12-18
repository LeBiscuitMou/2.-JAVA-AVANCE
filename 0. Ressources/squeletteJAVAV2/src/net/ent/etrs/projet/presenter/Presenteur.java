package net.ent.etrs.projet.presenter;

import net.ent.etrs.projet.models.facade.FacadeMetier;
import net.ent.etrs.projet.presenter.exception.PresenteurException;
import net.ent.etrs.projet.presenter.references.ConstantesPresenteur;
import net.ent.etrs.projet.views.facade.FacadeView;

import java.util.List;
import java.util.Objects;

public class Presenteur {

    FacadeMetier facadeMetier;

    FacadeView facadeView;

    private boolean estInitialise;

    public Presenteur(FacadeMetier facadeMetier, FacadeView facadeView) throws PresenteurException {
        this.setFacadeMetier(facadeMetier);
        this.setFacadeView(facadeView);
    }

    public void setFacadeMetier(FacadeMetier facadeMetier) throws PresenteurException {
        if (Objects.isNull(facadeMetier)) {
            throw new PresenteurException(ConstantesPresenteur.FACADE_MET_ERROR);
        }
        this.facadeMetier = facadeMetier;
    }

    public void setFacadeView(FacadeView facadeView) throws PresenteurException {
        if (Objects.isNull(facadeView)) {
            throw new PresenteurException(ConstantesPresenteur.FACADE_VIEW_ERROR);
        }
        this.facadeView = facadeView;
    }

    public void start() {

    }

    private void gererChoix(int choix) {
        switch (choix) {
            case 1 -> initialiser();
            case 2 ->
            case 3 ->
            case 4 ->
            case 5 ->
            case 6 ->
            case 7 ->
            case 8 ->
        }
    }

    /**
     * Methode fournie
     */
    private void initialiser() {

    }
}
