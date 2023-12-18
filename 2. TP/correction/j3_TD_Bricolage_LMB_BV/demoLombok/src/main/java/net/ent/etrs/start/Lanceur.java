package net.ent.etrs.start;

import net.ent.etrs.models.entities.EntitiesFactory;
import net.ent.etrs.models.entities.Personne;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Lanceur {

    public static void main(String[] args) {
        Personne p1 = EntitiesFactory.fabriquerPersonne(null,"",LocalDate.now());
        System.out.println(p1);
    }
}
