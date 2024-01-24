package net.ent.etrs.zoo.models.entities;

import lombok.*;
import net.ent.etrs.zoo.models.references.Type;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "uuid")
public class Animal {
    private String uuid = UUID.randomUUID().toString();

    @Getter
    @Setter
    private String nom;

    @Getter
    @Setter
    private Integer poids;

    @Getter
    @Setter
    private Float taille;

    @Getter
    @Setter
    private Boolean dangerous;

    @Getter
    @Setter
    private LocalDate dateNaissance;

    @Getter
    @Setter
    private Type type;
}