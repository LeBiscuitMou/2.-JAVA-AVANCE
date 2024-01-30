package net.ent.etrs.geststage.models.entities;

import net.ent.etrs.geststage.models.entities.references.Matiere;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Stagiaire extends AbstractEntity {
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private Map<Matiere, Float> notes = new HashMap<>();
    private Float moyenne;
}
