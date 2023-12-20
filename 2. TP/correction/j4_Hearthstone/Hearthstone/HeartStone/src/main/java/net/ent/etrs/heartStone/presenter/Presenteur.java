package net.ent.etrs.heartStone.presenter;

import lombok.Setter;
import net.ent.etrs.heartStone.models.entities.Carte;
import net.ent.etrs.heartStone.models.facades.FacadeMetier;
import net.ent.etrs.heartStone.models.facades.exception.BusinessException;
import net.ent.etrs.heartStone.view.FacadeView;
import net.ent.etrs.heartStone.view.commons.utils.AffichageConsole;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class Presenteur {
    //LBK
    @Setter
    //BV
    @NotNull(message = "La facade metier est null")
    FacadeMetier facadeMetier;
    //LBK
    @Setter
    //BV
    @NotNull(message = "La facade vue est null")
    FacadeView facadeView;

    public Presenteur(FacadeMetier facadeMetier, FacadeView facadeView) {
        this.setFacadeMetier(facadeMetier);
        this.setFacadeView(facadeView);
    }

    public void start() {
        int choix;
        do {
            List<String> menu = facadeView.afficherMenuPrincipal();
            choix = facadeView.lectureChoix(menu.size());
            gererChoix(choix);
        } while (choix != 0);

    }
    private void gererChoix(int choix) {
        switch (choix) {
            case 1 ->creerCarte();
            case 2 ->afficherCarte();
            case 3 ->afficherCarteParType();
            case 4 ->afficherCarteParCLasse();
            case 5 ->supprimerCarte();
            case 6 ->afficherCarteParMois();
        }
    }

    private void creerCarte() {
        try {
            this.facadeMetier.sauvegarderCarte(facadeView.saisirCarte());
        } catch (BusinessException e) {
            facadeView.afficherMessageErreur(e.getMessage());
        }
    }

    private void afficherCarte() {
        try {
            facadeMetier.getLesCarte().forEach(System.out::println);
        } catch (BusinessException e) {
            facadeView.afficherMessageErreur(e.getMessage());
        }
    }

    private void afficherCarteParType() {
        facadeMetier.getCarteByType(facadeView.choisirTypeCarte()).forEach(System.out::println);
    }

    private void afficherCarteParCLasse() {
        facadeMetier.getCarteByClasse(facadeView.choisirClasses()).forEach(System.out::println);
    }

    private void supprimerCarte() {
        try {
            facadeMetier.supprimerCarte(facadeView.choisirCarte(facadeMetier.getLesCarte()));
            AffichageConsole.afficherMessageAvecSautLigne("Carte supprimer !");
        } catch (BusinessException e) {
            facadeView.afficherMessageErreur(e.getMessage());
        }
    }
    private void afficherCarteParMois() {
        for (Map.Entry<java.time.Month, List<Carte>> entry : this.facadeMetier.getCarteByMonth().entrySet()) {
            facadeView.afficherMessage(String.format("- %d :", entry.getKey().getValue()));
            for (Carte c : entry.getValue()) {
                facadeView.afficherMessage(String.format("%s", "\t" +  c.toString()));
            }
        }

    }
}
