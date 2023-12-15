package net.ent.etrs.cycliste.model;

import net.ent.etrs.cycliste.model.entities.Cycliste;
import net.ent.etrs.cycliste.model.entities.EntitiesFactory;
import net.ent.etrs.cycliste.model.entities.Epreuve;
import net.ent.etrs.cycliste.model.entities.Equipe;
import net.ent.etrs.cycliste.model.entities.exceptions.CyclisteRuntimeException;
import net.ent.etrs.cycliste.model.entities.references.TypeSpecialite;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DemoCollection {
    
    public static Set<Cycliste> cyclistes = new HashSet<>();
    
    public static Set<Epreuve> epreuves = new HashSet<>();
    
    public static void main(String[] args) throws Exception {
        
        cyclistes.addAll(creerArkeaSamsic().getCyclistes());
        cyclistes.addAll(creerAg2rLaMondiale().getCyclistes());
        cyclistes.addAll(creerQuickStep().getCyclistes());
        
        epreuves.addAll(creerParisNice().toList());
        epreuves.addAll(creerParisRoubaix().toList());
        epreuves.addAll(creerCriteriumDauphine().toList());
        epreuves.addAll(creerTourFrance().toList());
        
        Random random = new Random();
        for (Cycliste cycliste : cyclistes){
            for (Epreuve e : epreuves) {
                cycliste.modifierPalmares(e, random.nextInt(20)+1);
            }
        }
        
        for (Cycliste cycliste : cyclistes){
            System.out.println(cycliste);
            for (Map.Entry<Epreuve, Integer> e : cycliste.getPalmares().entrySet()) {
                System.out.println(e.getKey().getNom() + " " + e.getValue());
            }
        }
        
    }
    
    public static Stream<Epreuve> creerParisNice() throws Exception {
        return Stream.of(EntitiesFactory.fabriquerEpreuve("PARIS-NICE", LocalDate.of(2019, Month.MARCH, 10), LocalDate.of(2019, Month.MARCH, 13)),
                EntitiesFactory.fabriquerEpreuve("PARIS-NICE", LocalDate.of(2018,
                        Month.MARCH, 4), LocalDate.of(2018, Month.MARCH, 11)),
                EntitiesFactory.fabriquerEpreuve("PARIS-NICE", LocalDate.of(2017,
                        Month.MARCH, 5), LocalDate.of(2017, Month.MARCH, 12)),
                EntitiesFactory.fabriquerEpreuve("PARIS-NICE", LocalDate.of(2016,
                        Month.MARCH, 6), LocalDate.of(2016, Month.MARCH, 13)));
    }
    public static Stream<Epreuve> creerParisRoubaix() throws Exception {
        return Stream.of(EntitiesFactory.fabriquerEpreuve("PARIS-ROUBAIX", LocalDate.of(2019, Month.APRIL, 14)),
                EntitiesFactory.fabriquerEpreuve("PARIS-ROUBAIX", LocalDate.of(2018, Month.APRIL, 8)),
                EntitiesFactory.fabriquerEpreuve("PARIS-ROUBAIX", LocalDate.of(2017, Month.APRIL, 9)),
                EntitiesFactory.fabriquerEpreuve("PARIS-ROUBAIX", LocalDate.of(2016, Month.APRIL, 10)));
    }
    public static Stream<Epreuve> creerCriteriumDauphine() throws Exception {
        return Stream.of(EntitiesFactory.fabriquerEpreuve("CRITERIUM DU DAUPHINE", LocalDate.of(2019, Month.JUNE, 9), LocalDate.of(2019, Month.JUNE, 16)),
                EntitiesFactory.fabriquerEpreuve("CRITERIUM DU DAUPHINE",
                        LocalDate.of(2018, Month.JUNE, 3), LocalDate.of(2018, Month.JUNE, 10)),
                EntitiesFactory.fabriquerEpreuve("CRITERIUM DU DAUPHINE",
                        LocalDate.of(2017, Month.JUNE, 4), LocalDate.of(2017, Month.JUNE, 11)),
                EntitiesFactory.fabriquerEpreuve("CRITERIUM DU DAUPHINE",
                        LocalDate.of(2016, Month.JUNE, 5), LocalDate.of(2016, Month.JUNE, 12)));
    }
    public static Stream<Epreuve> creerTourFrance() throws Exception {
        return Stream.of(EntitiesFactory.fabriquerEpreuve("TOUR DE FRANCE", LocalDate.of(2019, Month.JULY, 6), LocalDate.of(2019, Month.JULY, 28)),
                EntitiesFactory.fabriquerEpreuve("TOUR DE FRANCE", LocalDate.of(2018,
                        Month.JULY, 7), LocalDate.of(2018, Month.JULY, 29)),
                EntitiesFactory.fabriquerEpreuve("TOUR DE FRANCE", LocalDate.of(2017,
                        Month.JULY, 1), LocalDate.of(2017, Month.JULY, 23)),
                EntitiesFactory.fabriquerEpreuve("TOUR DE FRANCE", LocalDate.of(2016,
                        Month.JULY, 2), LocalDate.of(2016, Month.JULY, 24)));
    }
    public static Equipe creerArkeaSamsic() throws Exception {
        Equipe arkeaSamsic = EntitiesFactory.fabriquerEquipe("ARKEA_SAMSIC",
                EntitiesFactory.fabriquerPersonne("Hinault", "sébastien", LocalDate.of(1965, Month.APRIL, 1)));
        arkeaSamsic.ajouterCycliste(
                EntitiesFactory.fabriquerCycliste("Barguil", "Waren", LocalDate.of(1991, Month.OCTOBER, 28), TypeSpecialite.LEADER));
        arkeaSamsic.ajouterCycliste(
                EntitiesFactory.fabriquerCycliste("Greipel", "André",LocalDate.of(1982, Month.JULY, 16),TypeSpecialite.SPRINTER));
        arkeaSamsic.ajouterCycliste(
                EntitiesFactory.fabriquerCycliste("Bouet", "Maxime", LocalDate.of(1986, Month.NOVEMBER, 3),TypeSpecialite.DOMESTIQUE));
        arkeaSamsic.ajouterCycliste(
                EntitiesFactory.fabriquerCycliste("Moinard", "Amael",LocalDate.of(1982, Month.FEBRUARY, 2),TypeSpecialite.DOMESTIQUE));
        arkeaSamsic.ajouterCycliste(
                EntitiesFactory.fabriquerCycliste("Delaplace", "Anthony", LocalDate.of(1982, Month.SEPTEMBER, 11),TypeSpecialite.DOMESTIQUE));
        arkeaSamsic.ajouterCycliste(
                EntitiesFactory.fabriquerCycliste("Ledanois", "Kevin",LocalDate.of(1993, Month.JULY, 13),TypeSpecialite.DOMESTIQUE));
        return arkeaSamsic;
    }
    
    public static Equipe creerAg2rLaMondiale() throws Exception {
        Equipe ag2r = EntitiesFactory.fabriquerEquipe("ag2r la mondiale",
                EntitiesFactory.fabriquerPersonne("Hinault", "sébastien", LocalDate.of(1965, Month.APRIL, 1)));
        ag2r.ajouterCycliste(
                EntitiesFactory.fabriquerCycliste("Bardet", "Henri", LocalDate.of(1990, Month.NOVEMBER, 9),TypeSpecialite.LEADER));
        ag2r.ajouterCycliste(
                EntitiesFactory.fabriquerCycliste("Cherel", "Pierre", LocalDate.of(1986, Month.MARCH, 17),TypeSpecialite.DOMESTIQUE));
        ag2r.ajouterCycliste(
                EntitiesFactory.fabriquerCycliste("Gallopin", "Arnaud", LocalDate.of(1988, Month.MAY, 24),TypeSpecialite.BAROUDEUR));
        return ag2r;
    }
    public static Equipe creerQuickStep() throws Exception {
        Equipe quickstep = EntitiesFactory.fabriquerEquipe("Quick step",
                EntitiesFactory.fabriquerPersonne("Hinault", "sébastien", LocalDate.of(1965, Month.APRIL, 1)));
        quickstep.ajouterCycliste(
                EntitiesFactory.fabriquerCycliste("Alaphilippe", "Luc", LocalDate.of(1992, Month.JUNE, 11), TypeSpecialite.LEADER));
        quickstep.ajouterCycliste(
                EntitiesFactory.fabriquerCycliste("Asgreen", "Nicolas", LocalDate.of(1995, Month.FEBRUARY, 8),TypeSpecialite.DOMESTIQUE));
        quickstep.ajouterCycliste(
                EntitiesFactory.fabriquerCycliste("Lampaert", "Grégoire", LocalDate.of(1981, Month.APRIL, 10),TypeSpecialite.PUNCHEUR));
        quickstep.ajouterCycliste(
                EntitiesFactory.fabriquerCycliste("Mørkøv", "Jean-Paul", LocalDate.of(1985, Month.APRIL, 30),TypeSpecialite.PUNCHEUR));
        quickstep.ajouterCycliste(
                EntitiesFactory.fabriquerCycliste("Viviani", "Philippe", LocalDate.of(1989, Month.FEBRUARY, 7),TypeSpecialite.DOMESTIQUE));
        return quickstep;
    }
    
}
