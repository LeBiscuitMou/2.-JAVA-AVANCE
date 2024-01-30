package net.ent.etrs.geststage.models.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


public class Stage extends AbstractEntity {

    private String code;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Set<Stagiaire> stagiaires = new HashSet<>();

}
