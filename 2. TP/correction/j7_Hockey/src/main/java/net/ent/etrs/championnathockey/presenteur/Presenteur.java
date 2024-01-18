package net.ent.etrs.championnathockey.presenteur;

import net.ent.etrs.championnathockey.models.facades.*;


public class Presenteur {
    FacadeJoueur facadeJoueur;
    FacadeChampionnat facadeChampionnat;
    FacadeEquipe facadeEquipe;

    FacadeChargementFichier facadeChargementFichier;

    public Presenteur() {
        facadeJoueur = FacadeFactory.fabriquerFacadeJoueur();
        facadeChampionnat = FacadeFactory.fabriquerFacadeChampionnat();
        facadeEquipe = FacadeFactory.fabriquerFacadeEquipe();
        facadeChargementFichier = FacadeFactory.fabriquerFacadeChargementFichier();

        try {
            facadeChargementFichier.initialisation();
        } catch (Exception e) {
            System.out.println("ERR Init :" + e.getMessage());
        }
    }


}
