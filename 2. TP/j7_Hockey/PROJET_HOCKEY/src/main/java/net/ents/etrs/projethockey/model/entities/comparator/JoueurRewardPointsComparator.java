package net.ents.etrs.projethockey.model.entities.comparator;

import net.ents.etrs.projethockey.model.entities.Equipe;
import net.ents.etrs.projethockey.model.entities.Joueur;

import java.util.Comparator;

public class JoueurRewardPointsComparator implements Comparator<Equipe> {


    @Override
    public int compare(Equipe o1, Equipe o2) {
        return Integer.compare(o1.getTotalPoints(), o2.getTotalPoints());
    }
}




