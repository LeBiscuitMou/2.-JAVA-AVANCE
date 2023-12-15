package net.ent.etrs.models.entities;

import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(of = {"nom", "prenom"})
@ToString(of = {"nom", "prenom", "dateNaissance"})
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Personne {
    // LBK
    @Getter
    @Setter
    @NonNull
    // BV
    @NotNull(message = "Le nom ne peut pas être null")
    @NotBlank(message = "Le nom ne peut pas être vide")
    private String nom;

    // LBK
    @Getter
    @Setter
    @NonNull
    // BV
    @NotNull(message = "Le prenom ne peut pas être null")
    @NotBlank(message = "Le prenom ne peut pas être vide")
    private String prenom;

    // LBK
    @Getter
    @Setter
    @NonNull
    // BV
    @NotNull(message = "La date de naissance ne peut pas être null")
    @PastOrPresent(message = "La date de naissance ne peut pas être dans le futur")
    private LocalDate dateNaissance;

    private List<Personne> enfants;
}