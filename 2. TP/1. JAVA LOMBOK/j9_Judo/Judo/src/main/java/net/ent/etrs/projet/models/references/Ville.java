package net.ent.etrs.projet.models.references;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Ville {

    RENNES("Rennes"),
    TOURS("Tours"),
    PARIS("Paris"),
    BORDEAUX("Bordeaux"),
    TOULOUSE("Marseille");

    private final String libelle;



}
