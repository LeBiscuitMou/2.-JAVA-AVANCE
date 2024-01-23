package net.ent.etrs.fistJavaFX.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "uuid")
@AllArgsConstructor
public class Personne {
    private String uuid = UUID.randomUUID().toString();

    @Setter
    private String nom;

    @Setter
    private String prenom;

    @Setter
    private LocalDate dateDeNaissance;
}