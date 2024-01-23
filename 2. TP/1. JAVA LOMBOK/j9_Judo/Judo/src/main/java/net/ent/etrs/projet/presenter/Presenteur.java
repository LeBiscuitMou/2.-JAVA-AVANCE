package net.ent.etrs.projet.presenter;

import net.ent.etrs.projet.models.exceptions.*;
import net.ent.etrs.projet.models.facades.impl.FacadeFactory;
import net.ent.etrs.projet.models.references.*;
import net.ent.etrs.projet.models.entities.*;
import net.ent.etrs.projet.presenter.exceptions.PresenteurException;

import java.lang.Exception;
import java.util.Objects;

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
            initialisation();
        } catch (Exception e) {
            System.out.println("ERR Init :" + e.getMessage());
        }
    }
}
