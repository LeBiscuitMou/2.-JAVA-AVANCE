package net.ent.etrs.projetjavamaier.models.entities.comparator;

import net.ent.etrs.projetjavamaier.models.entities.Console;

import java.util.Comparator;

public class ConsoleComparator implements Comparator<Console> {
    @Override
    public int compare(Console o1, Console o2) {
        return o1.getNom().compareTo(o2.getNom());
    }
}
