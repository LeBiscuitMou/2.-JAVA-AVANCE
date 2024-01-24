package net.ent.etrs.firstjavafx.model.entities;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Personne {

    @Getter @Setter
    private String prenom;

    @Getter @Setter
    private String nom;

    @Getter @Setter
    private LocalDate ddn;

}
