package net.ent.etrs.cartes.start;

import net.ent.etrs.cartes.models.entities.Caracteristique;
import net.ent.etrs.cartes.models.entities.Carte;
import net.ent.etrs.cartes.models.entities.EntitiesFactory;
import net.ent.etrs.cartes.models.entities.references.Classe;
import net.ent.etrs.cartes.models.entities.references.TypeCarte;

import java.time.LocalDate;

public class Lanceur {
    public static void main(String[] args) {
        Caracteristique caracteristique = EntitiesFactory.fabriquerCaracteristique("incroyable", "C'est vraiment incroyable");
        Carte carte = EntitiesFactory.fabriquerCarte("dingue !", 69, LocalDate.of(2021, 12, 12), TypeCarte.DEMON, Classe.CHASSEUR_DE_DEMONS);

        System.out.println(caracteristique);
        System.out.println(carte);

        Caracteristique caracteristique1 = EntitiesFactory.fabriquerCaracteristique("", "      ");
        Carte carte1 = EntitiesFactory.fabriquerCarte("", -69, LocalDate.of(2969, 10, 24), null, null);

        System.out.println(caracteristique1);
        System.out.println(carte1);
    }
}