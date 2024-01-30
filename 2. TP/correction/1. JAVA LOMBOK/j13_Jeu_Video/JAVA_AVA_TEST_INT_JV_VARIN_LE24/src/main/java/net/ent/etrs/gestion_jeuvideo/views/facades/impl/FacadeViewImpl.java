package net.ent.etrs.gestion_jeuvideo.views.facades.impl;


import net.ent.etrs.gestion_jeuvideo.models.entities.Console;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Genre;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Pays;
import net.ent.etrs.gestion_jeuvideo.views.commons.utils.*;
import net.ent.etrs.gestion_jeuvideo.views.facades.FacadeView;
import net.ent.etrs.gestion_jeuvideo.views.references.ConstView;

import java.util.*;


public class FacadeViewImpl implements FacadeView {
    protected FacadeViewImpl() {

    }

    /**
     * Permet d'afficher un message d'erreur.
     *
     * @param message le message de l'exception
     */
    @Override
    public void afficherMessageErreur(String message) {
        AffichageConsole.afficherErreur(message);
    }

    /**
     * Permet d'afficher un message à l'utilisateur.
     *
     * @param message le message pour l'utilisateur
     */
    @Override
    public void afficherMessage(String message) {
        AffichageConsole.afficherMessageAvecSautLigne(message);
    }

    /**
     * Permet d'afficher le menu principal en le construisant et de lire le choix de la saisie utilisateur.
     *
     * @return le choix effectué par l'utilisateur
     */
    @Override
    public List<String> afficherMenuPrincipal() {
        List<String> listOptions = new ArrayList<>(Arrays.asList(ConstView.MENU));
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < listOptions.size(); i++) {
            map.put(i, listOptions.get(i));
        }
        AffichageConsole.afficherMenuEntoure(map, ConstView.NOM_PROGRAMME);
        return listOptions;
    }

    /**
     * Permet de lire le choix de la saisie utilisateur
     *
     * @param size le choix maximal
     * @return le choix effectué par l'utilisateur
     */
    @Override
    public int lectureChoix(int size) {
        return LectureConsole.lectureChoixInt(0, size-1);
    }

    @Override
    public Console choisirConsole(List<Console> consoles) {
        return ChoixT.selectFrom(consoles);
    }

    @Override
    public Genre choisirGenre() {
        return ChoixT.selectFrom(Genre.values());
    }

    @Override
    public Pays choisirPays() {
        return ChoixT.selectFrom(Pays.values());
    }

    @Override
    public String saisirText(String message) {
        return LectureConsole.lectureChaineCaracteres(message);
    }


}
