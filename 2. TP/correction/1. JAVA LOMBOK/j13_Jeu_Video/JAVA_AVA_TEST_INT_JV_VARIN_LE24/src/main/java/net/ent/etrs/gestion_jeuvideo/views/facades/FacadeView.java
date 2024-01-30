package net.ent.etrs.gestion_jeuvideo.views.facades;


import net.ent.etrs.gestion_jeuvideo.models.entities.Console;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Genre;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Pays;

import java.util.List;

public interface FacadeView {
    /**
     * Permet d'afficher un message d'erreur.
     *
     * @param message le message de l'exception
     */
    void afficherMessageErreur(String message);

    /**
     * Permet d'afficher un message à l'utilisateur.
     *
     * @param message le message pour l'utilisateur
     */
    void afficherMessage(String message);

    /**
     * Permet d'afficher le menu principal en le construisant et de lire le choix de la saisie utilisateur.
     *
     * @return le choix effectué par l'utilisateur
     */
    List<String> afficherMenuPrincipal();

    /**
     * Permet de lire le choix de la saisie utilisateur.
     *
     * @param size le choix maximal
     * @return le choix effectué par l'utilisateur
     */
    int lectureChoix(int size);

    Console choisirConsole(List<Console> consoles);

    Genre choisirGenre();

    Pays choisirPays();

    String saisirText(String message);
}
