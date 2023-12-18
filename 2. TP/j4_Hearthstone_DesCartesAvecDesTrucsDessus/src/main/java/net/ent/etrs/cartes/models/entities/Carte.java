package net.ent.etrs.cartes.models.entities;

import lombok.*;
import net.ent.etrs.cartes.models.entities.references.Classe;
import net.ent.etrs.cartes.models.entities.references.TypeCarte;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(of = "nom")
@ToString(of = {"nom", "cout", "dateSortie", "typeCarte", "classe"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Carte extends AbstractEntity {
    // LBK
    @Getter
    @Setter
    @NonNull
    // BV
    @NotNull(message = "le nom ne doit pas être NULL")
    @NotBlank(message = "Le nom ne doit pas être vide")
    private String nom;

    // LBK
    @Getter
    @Setter
    @NonNull
    // BV
    @NotNull(message = "le cout ne doit pas être NULL")
    @PositiveOrZero(message = "Le cout ne doit pas être négatif")
    private Integer cout;

    // LBK
    @Getter
    @Setter
    @NonNull
    // BV
    @NotNull(message = "la date de sortie ne doit pas être NULL")
    @Past(message = "la date de sortie ne doit pas être dans le futur")
    private LocalDate dateSortie;

    // LBK
    @Getter
    @Setter
    @NonNull
    // BV
    @NotNull(message = "le type de carte ne doit pas être NULL")
    private TypeCarte typeCarte;

    // LBK
    @Getter
    @Setter
    @NonNull
    // BV
    @NotNull(message = "la classe ne doit pas être NULL")
    private Classe classe;

    @Singular
    private List<Caracteristique> caracteristiques = new ArrayList<>();

    public List<Caracteristique> getCaracteristiques() {
        return Collections.unmodifiableList(caracteristiques);
    }
}