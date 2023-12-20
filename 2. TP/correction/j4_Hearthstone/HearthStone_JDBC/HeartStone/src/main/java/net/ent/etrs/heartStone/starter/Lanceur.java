package net.ent.etrs.heartStone.starter;

import net.ent.etrs.heartStone.models.entities.Carte;
import net.ent.etrs.heartStone.models.entities.EntitiesFactory;
import net.ent.etrs.heartStone.models.facades.impl.FactoryFacadeMetier;
import net.ent.etrs.heartStone.models.references.Classe;
import net.ent.etrs.heartStone.models.references.TypeCarte;
import net.ent.etrs.heartStone.presenter.Presenteur;
import net.ent.etrs.heartStone.view.impl.FactoryFacadeView;

import java.time.LocalDate;


public class Lanceur {
    public static void main(String[] args) {
        Presenteur p = new Presenteur(FactoryFacadeMetier.fabriquerFacadeMetier(), FactoryFacadeView.fabriquerFacadeView());
        p.start();



     /*   Carte carte = EntitiesFactory.fabriquerCarte("test", 10, LocalDate.of(2000, 10, 10), TypeCarte.ELEM, Classe.DRUIDE );

        System.out.println(carte);*/
    }
}
