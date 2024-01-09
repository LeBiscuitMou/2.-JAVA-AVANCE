package net.ent.etrs.championnathockey.models.entities.comparator;

import net.ent.etrs.championnathockey.models.entities.Equipe;
import net.ent.etrs.championnathockey.models.entities.Joueur;

import java.util.Comparator;

public class EquipePointComparator implements Comparator<Equipe> {

    @Override
    public int compare(Equipe o1, Equipe o2) {
        int nbPointsE1 = 0;
        int nbPointsE2 = 0;
        for (Joueur j : o1.getListeJoueurs()) {
            nbPointsE1 += j.getNbPoint();
        }
        for (Joueur j : o2.getListeJoueurs()) {
            nbPointsE2 += j.getNbPoint();
        }

        return Integer.compare(nbPointsE1, nbPointsE2);

    }
}
