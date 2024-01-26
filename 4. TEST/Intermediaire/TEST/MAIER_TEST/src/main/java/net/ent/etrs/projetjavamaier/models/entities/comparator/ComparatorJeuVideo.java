package net.ent.etrs.projetjavamaier.models.entities.comparator;

import net.ent.etrs.projetjavamaier.models.entities.JeuVideo;

import java.util.Comparator;

public class ComparatorJeuVideo implements Comparator<JeuVideo> {
    @Override
    public int compare(JeuVideo o1, JeuVideo o2) {
        return o1.getDateSortie().compareTo(o2.getDateSortie());
    }
}
