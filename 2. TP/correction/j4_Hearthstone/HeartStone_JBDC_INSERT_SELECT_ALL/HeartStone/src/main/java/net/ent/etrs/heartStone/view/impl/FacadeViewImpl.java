package net.ent.etrs.heartStone.view.impl;


import net.ent.etrs.heartStone.models.entities.Caracteristique;
import net.ent.etrs.heartStone.models.entities.Carte;
import net.ent.etrs.heartStone.models.entities.EntitiesFactory;
import net.ent.etrs.heartStone.models.references.Classe;
import net.ent.etrs.heartStone.models.references.TypeCarte;
import net.ent.etrs.heartStone.view.FacadeView;
import net.ent.etrs.heartStone.view.commons.utils.AffichageConsole;
import net.ent.etrs.heartStone.view.commons.utils.LectureConsole;
import net.ent.etrs.heartStone.view.commons.utils.Utils;
import net.ent.etrs.heartStone.view.references.ConstantesView;

import java.time.LocalDate;
import java.util.*;

public class FacadeViewImpl implements FacadeView {
    @Override
    public void afficherMessageErreur(String message) {
        AffichageConsole.afficherErreur(message);
    }

    @Override
    public void afficherMessage(String message) {
        AffichageConsole.afficherMessageAvecSautLigne(message);
    }



    @Override
    public List<String> afficherMenuPrincipal() {
        List<String> menuPrincipals = new ArrayList<>();
        menuPrincipals.add("Créer une carte");
        menuPrincipals.add("Afficher toutes les cartes");
        menuPrincipals.add("Afficher cartes par type");
        menuPrincipals.add("Afficher cartes par classe");
        menuPrincipals.add("Supprimer cartes");
        menuPrincipals.add("Afficher cartes par mois");

        AffichageConsole.afficherMenuEntoureAvecOptionSortie(menuPrincipals, "Menu principale");
        return menuPrincipals;
    }

    @Override
    public int lectureChoix(int size) {
        return LectureConsole.lectureChoixInt(0, size);
    }

    /**
     * Permet de lire le choix de la saisie utilisateur pour un Oui / Non
     *
     * @return le choix effectué par l'utilisateur
     */
    @Override
    public boolean lectureChoixBoolean() {
        return LectureConsole.lectureBoolean("Souhaitez vous ajouter un autre jeu de société à votre commande");
    }

    @Override
    public Carte saisirCarte() {
        String nom = LectureConsole.lectureChaineCaracteres("Entrer le nom: ");
        Integer cout = LectureConsole.lectureEntier("Entrer le cout: ");
        LocalDate dateSortie = LectureConsole.lectureLocalDate("Saisir la date de sortie: ", ConstantesView.PATTERN_GDH);
        Classe classe = choisirClasses();
        TypeCarte tc = choisirTypeCarte();

        //TODO Gérer les caractéristiques
        Set<Caracteristique> caract = new HashSet<>();

        return EntitiesFactory.fabriquerCarte(nom, cout,dateSortie,tc, classe,caract);

    }

    @Override
    public Classe choisirClasses() {
        return Utils.recuperationObjet(Arrays.asList(Classe.values()));
    }
    @Override
    public TypeCarte choisirTypeCarte() {
        return Utils.recuperationObjet(Arrays.asList(TypeCarte.values()));
    }

    @Override
    public Carte choisirCarte(List<Carte> carteList) {
        return Utils.recuperationObjet(carteList);
    }


}
