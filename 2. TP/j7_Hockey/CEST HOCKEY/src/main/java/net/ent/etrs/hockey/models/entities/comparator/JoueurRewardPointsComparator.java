package net.ent.etrs.hockey.models.entities.comparator;

import net.ent.etrs.hockey.models.entities.Equipe;

import java.util.Comparator;

public class JoueurRewardPointsComparator implements Comparator<Equipe> {


    @Override
    public int compare(Equipe o1, Equipe o2) {
        return Integer.compare(o1.getTotalPoints(), o2.getTotalPoints());
    }
}