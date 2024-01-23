package net.ent.etrs.sacADos.model.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@ToString
@EqualsAndHashCode(of = {"uuid"})
public class Sac implements Comparable<Sac> {
    
    @Getter
    private UUID uuid = UUID.randomUUID();
    
    @Getter
    private Double score;
    
    @Getter
    private Set<Objet> listeObjets = new LinkedHashSet<>();
    
    public void ajouterObjet(Objet pObjet) {
        this.listeObjets.add(pObjet);
    }
    
    @Override
    public int compareTo(Sac pSac) {
        return this.getScore().compareTo(pSac.getScore());
    }
    
    public void calculerScore() {
        //TODO
    }
    
}
