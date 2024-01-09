package net.ent.etrs.hockey.models.entities.references;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Poste {
    GARDIEN(1),
    DEFENSEUR(4),
    ATTAQUANT(6);

    /**
     * nombre de joueurs minimum dans le poste pour qu'une équipe soit valide
     */
    private final Integer minJoueur;
}
