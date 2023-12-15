package net.ent.etrs.start;

import net.ent.etrs.models.entities.EntitiesFactory;
import net.ent.etrs.models.entities.Personne;

import java.time.LocalDate;

public class Lanceur {
    public static void main(String[] args) {
        Personne p1 = EntitiesFactory.fabriquerPersonne("Poinsot", "Lucas", LocalDate.of(2001, 7, 10));
        Personne p2 = EntitiesFactory.fabriquerPersonne(null, "", LocalDate.now());

        System.out.println(p1);
        System.out.println(p2);
    }
}