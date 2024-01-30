package net.ent.etrs.gestion_jeuvideo.presenter;

import net.ent.etrs.gestion_jeuvideo.models.entities.Console;
import net.ent.etrs.gestion_jeuvideo.models.entities.JeuVideo;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Genre;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Pays;
import net.ent.etrs.gestion_jeuvideo.models.facades.FacadeMetier;
import net.ent.etrs.gestion_jeuvideo.models.exceptions.*;
import net.ent.etrs.gestion_jeuvideo.models.facades.impl.FacadeMetierFactory;
import net.ent.etrs.gestion_jeuvideo.models.facades.interfaces.IFacadeChargementFichier;
import net.ent.etrs.gestion_jeuvideo.models.facades.interfaces.IFacadeConsole;
import net.ent.etrs.gestion_jeuvideo.models.facades.interfaces.IFacadeFabriquant;
import net.ent.etrs.gestion_jeuvideo.models.facades.interfaces.IFacadeJeuVideo;
import net.ent.etrs.gestion_jeuvideo.models.references.*;
import net.ent.etrs.gestion_jeuvideo.presenter.exceptions.PresenteurException;
import net.ent.etrs.gestion_jeuvideo.views.facades.FacadeView;
import net.ent.etrs.gestion_jeuvideo.views.facades.impl.FacadeViewFactory;
import net.ent.etrs.gestion_jeuvideo.views.references.ConstView;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Presenteur {
    /* ******************************** VARIABLES ******************************** */
    private IFacadeChargementFichier fcharge;
    private IFacadeConsole fConsole;
    private IFacadeJeuVideo fJeu;
    private IFacadeFabriquant fFabriquant;
    private FacadeView fVue;
    private boolean initialiser = false;

    /* ******************************* CONSTRUCTOR ******************************* */
    protected Presenteur() throws PresenteurException {
        fVue = FacadeViewFactory.fabriquerFacadeVue();
        fcharge = FacadeMetierFactory.fabriquerFacadeChargementFichier();
        fConsole = FacadeMetierFactory.fabriquerFacadeConsole();
        fJeu = FacadeMetierFactory.fabriquerFacadeJeuVideo();
        fFabriquant = FacadeMetierFactory.fabriquerFacadeFabriquant();

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
                case 2 -> recupererJeuxVideoParGenre();
                case 3 -> recupererJeuxVideoDontLeNomCommenceParEtLeFabriquantEstDeTelPays();
                case 4 -> recupererLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays();
                case 5 -> recupererConsoleAvecLeurJeuxVideo();
                case 6 -> recupererDureeMoyenneSortieEntreJeuVideoPourUneConsole();
                case 0 -> quitter = true;
                default -> fVue.afficherMessage(ConstView.MESSAGE_ERREUR_MAIN_SWITCH);
            }
        }while (!quitter);

        fVue.afficherMessage(ConstView.MESSAGE_SORTIE_PROGRAMME);
    }

    private void recupererJeuxVideoParGenre() {
        try {
            Genre genre = fVue.choisirGenre();
            List<JeuVideo> jeuVideos = fJeu.recupererJeuxVideoParGenre(genre);
            if(jeuVideos.isEmpty()){
                fVue.afficherMessage("il n'y a pas de jeux vidéo de ce Genre");
            }else {
                jeuVideos.forEach(System.out::println);
            }

        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
    }

    private void recupererJeuxVideoDontLeNomCommenceParEtLeFabriquantEstDeTelPays() {
        try {
            Pays pays = fVue.choisirPays();
            String debutNomJV = fVue.saisirText("par quoi commence le jeu vidéo ?");
            List<JeuVideo> jeuVideos = fJeu.recupererJeuxVideoDontLeNomCommenceParEtLeFabriquantEstDeTelPays(debutNomJV, pays);
            if(jeuVideos.isEmpty()){
                fVue.afficherMessage("il n'y a pas de jeux vidéo comme ça");
            }else {
                jeuVideos.forEach(System.out::println);
            }
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
    }

    private void recupererLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays() {
        try {
            Pays pays = fVue.choisirPays();
            Console console = fConsole.recupererLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays(pays);
            System.out.println(console);
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
    }

    private void recupererConsoleAvecLeurJeuxVideo() {
        try {
            Map<Console, List<JeuVideo>> resultMap = fConsole.recupererConsoleAvecLeurJeuxVideo();
            for(Map.Entry<Console, List<JeuVideo>> entry : resultMap.entrySet()){
                System.out.println(entry.getKey());
                for(JeuVideo jeu : entry.getValue()){
                    System.out.println(" ".repeat(10) + jeu);
                }
            }
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
    }

    private void recupererDureeMoyenneSortieEntreJeuVideoPourUneConsole() {
        try {
            Console console = fVue.choisirConsole(fConsole.recupererlesConsoles());
            String message = fJeu.recupererDureeMoyenneSortieEntreJeuVideoPourUneConsole(console);
            System.out.println(message);
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
    }

    private void initialiser() {
        if(!initialiser){
            initialiser = true;
            try {
                fcharge.initialisation();
            } catch (BusinessException e) {
                fVue.afficherMessageErreur(e.getMessage());
            }
        }
        else {
            fVue.afficherMessage(ConstView.INITIALISATION_ALREADY_DONE);
        }
    }
}
