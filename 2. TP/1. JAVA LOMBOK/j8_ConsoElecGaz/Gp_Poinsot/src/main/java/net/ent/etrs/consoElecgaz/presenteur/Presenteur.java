package net.ent.etrs.consoElecgaz.presenteur;

import net.ent.etrs.consoElecgaz.models.facades.*;


public class Presenteur {
    IFacadeChargementFichier facadeChargementFichier;
    IFacadeConsommation facadeConsommation;
    IFacadeOperateur facadeOperateur;
    IFacadeRegion facadeRegion;
    IFacadeActivitePrincipale facadeActivitePrincipale;

    public Presenteur() {
        facadeChargementFichier = FacadeFactory.fabriquerFacadeChargementFichier();
        facadeConsommation = FacadeFactory.fabriquerFacadeConsomation();
        facadeOperateur = FacadeFactory.fabriquerFacadeOperateur();
        facadeRegion = FacadeFactory.fabriquerFacadeRegion();
        facadeActivitePrincipale = FacadeFactory.fabriquerFacadeActivitePrincipale();

        try {
            facadeChargementFichier.initialisation();
        } catch (Exception e) {
            System.out.println("ERR Init :" + e.getMessage());
        }
    }


}
