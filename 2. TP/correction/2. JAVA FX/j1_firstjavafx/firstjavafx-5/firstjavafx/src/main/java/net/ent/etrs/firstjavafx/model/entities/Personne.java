package net.ent.etrs.firstjavafx.model.entities;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "uuid")
@ToString
public class Personne {

    @Getter
    private String uuid = UUID.randomUUID().toString();

    @Getter @Setter
    private String prenom;

    @Getter @Setter
    private String nom;

    @Getter @Setter
    private LocalDate ddn;

}
