package net.ent.etrs.cycliste.model.entities;

import java.time.LocalDate;
import java.time.Month;

public class DemoStream {
    public static Equipe creerArkeaSamsic() throws ConstructionException, PersonneException,
            EquipeException {
        Equipe arkeaSamsic = EntitiesFactory.fabriquerEquipe("ARKEA_SAMSIC",
                EntitiesFactory.fabriquerPersonne("Hinault", "sébastien", LocalDate.of(1965, Month.APRIL, 1)));
        arkeaSamsic.ajouterCycliste(EntitiesFactory.fabriquerCycliste("Barguil", "Waren",
                LocalDate.of(1991, Month.OCTOBER, 28),TypeSpecialite.LEADER));
        arkeaSamsic.ajouterCycliste(EntitiesFactory.fabriquerCycliste("Greipel",
                "André",LocalDate.of(1982, Month.JULY, 16),TypeSpecialite.SPRINTER));
        arkeaSamsic.ajouterCycliste(EntitiesFactory.fabriquerCycliste("Bouet", "Maxime",
                LocalDate.of(1986, Month.NOVEMBER, 3),TypeSpecialite.DOMESTIQUE));
        arkeaSamsic.ajouterCycliste(EntitiesFactory.fabriquerCycliste("Moinard",
                "Amael",LocalDate.of(1982, Month.FEBRUARY, 2),TypeSpecialite.DOMESTIQUE));
        arkeaSamsic.ajouterCycliste(EntitiesFactory.fabriquerCycliste("Delaplace", "Anthony",
                LocalDate.of(1982, Month.SEPTEMBER, 11),TypeSpecialite.DOMESTIQUE));
        arkeaSamsic.ajouterCycliste(EntitiesFactory.fabriquerCycliste("Ledanois",
                "Kevin",LocalDate.of(1993, Month.JULY, 13),TypeSpecialite.DOMESTIQUE));
        return arkeaSamsic;
    }
    public static Equipe creerAg2rLaMondiale() throws ConstructionException, PersonneException,
            EquipeException {
        Equipe ag2r = EntitiesFactory.fabriquerEquipe("ag2r la mondiale",
                EntitiesFactory.fabriquerPersonne("Hinault", "sébastien", LocalDate.of(1965, Month.APRIL, 1)));
        ag2r.ajouterCycliste(EntitiesFactory.fabriquerCycliste("Bardet", "", LocalDate.of(1990,
                Month.NOVEMBER, 9),TypeSpecialite.LEADER));
        ag2r.ajouterCycliste(EntitiesFactory.fabriquerCycliste("Cherel", "", LocalDate.of(1986,
                Month.MARCH, 17),TypeSpecialite.DOMESTIQUE));
        ag2r.ajouterCycliste(EntitiesFactory.fabriquerCycliste("Gallopin", "", LocalDate.of(1988,
                Month.MAY, 24),TypeSpecialite.BAROUDEUR));
        return ag2r;
    }
    public static Equipe creerQuickStep() throws ConstructionException, PersonneException,
            EquipeException {
        Equipe quickstep = EntitiesFactory.fabriquerEquipe("Quick step",
                EntitiesFactory.fabriquerPersonne("Hinault", "sébastien", LocalDate.of(1965, Month.APRIL, 1)));
        quickstep.ajouterCycliste(EntitiesFactory.fabriquerCycliste("Alaphilippe", "",
                LocalDate.of(1992, Month.JUNE, 11), TypeSpecialite.LEADER));
        quickstep.ajouterCycliste(EntitiesFactory.fabriquerCycliste("Asgreen", "",
                LocalDate.of(1995, Month.FEBRUARY, 8), TypeSpecialite.DOMESTIQUE));
        quickstep.ajouterCycliste(EntitiesFactory.fabriquerCycliste("Lampaert", "",
                LocalDate.of(1981, Month.APRIL, 10), TypeSpecialite.PUNCHEUR));
        quickstep.ajouterCycliste(EntitiesFactory.fabriquerCycliste("Mørkøv", "",
                LocalDate.of(1985, Month.APRIL, 30), TypeSpecialite.PUNCHEUR));
        quickstep.ajouterCycliste(EntitiesFactory.fabriquerCycliste("Viviani", "",
                LocalDate.of(1989, Month.FEBRUARY, 7), TypeSpecialite.DOMESTIQUE));
        return quickstep;
    }
}