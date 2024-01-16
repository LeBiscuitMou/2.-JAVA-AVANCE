package net.ent.etrs.hockey.model.entities.references;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Ville {

    RENNES("Rennes"),
    TOURS("Tours"),
    PARIS("Paris"),
    BORDEAUX("Bordeaux"),
    TOULOUSE("Toulouse"),
    ANGERS("Angers"),
    ROUEN("Rouen"),
    CAEN("Caen"),
    AMIENS("Amiens"),
    GRENOBLE("Grenoble"),
    CERGY("Cergy"),
    ANGLET("Anglet"),
    LYON("Lyon"),
    BRIANCON("Briançon");


    private final String libelle;

}
