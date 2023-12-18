package net.ent.etrs.models.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@EqualsAndHashCode(of = {"nom", "prenom"})
@ToString(of = {"nom", "prenom","dateNaissance"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Personne {

    //LBK
    @Getter
    @Setter
    @NonNull
    //BV
    @NotNull(message = "Le nom ne doit pas être null")
    @NotBlank(message = "Le nom ne peut pas être vide")
    private String nom;

    @Getter
    @Setter
    @NonNull
    //BV
    @NotNull(message = "Le prenom ne doit pas être null")
    @NotBlank(message = "Le prenom ne peut pas être vide")
    String prenom;

    @Getter
    @Setter
    @NonNull
            //BV
            @NotNull(message = "La dateNaissance ne doit pas être null")
            @PastOrPresent(message = "La dateNaissance ne doit pas être dans le futur")
    LocalDate dateNaissance;


    List<Personne> enfants = new ArrayList<>();


    public List<Personne> getEnfants() {
        return Collections.unmodifiableList(enfants);
    }
}
