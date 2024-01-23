package net.ent.etrs.fistJavaFX.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@EqualsAndHashCode(of = "uuid")
@AllArgsConstructor
public class Personne {
    @Getter
    private String uuid = UUID.randomUUID().toString();

    @Getter
    @Setter
    private String nom;

    @Getter
    @Setter
    private String prenom;

    @Getter
    @Setter
    private LocalDate dateDeNaissance;
}