package net.ent.etrs.hockey.models.entities;

import lombok.*;
import net.ent.etrs.hockey.models.entities.references.Poste;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Table(name = "joueur", uniqueConstraints = @UniqueConstraint(name = "UK_joueur_nom_prenom_date", columnNames = {"nom", "prenom", "dateNaissance"}))
@EqualsAndHashCode(of = {"nom", "prenom", "dateNaissance"}, callSuper = false)
@ToString(of = {"nom", "prenom", "dateNaissance", "nbPoint", "poste"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Joueur extends AbstractEntity {
    @Getter
    @Setter
    @NotBlank
    @Length(min = 3, max = 30)
    @Column(name = "prenom", nullable = false, length = 30)
    private String prenom;

    @Getter
    @Setter
    @NotNull
    @Past(message = "La date de naissance ne peut pas être dans le présent ni dans le futur")
    @Column(name = "dateNaissance", nullable = false)
    private LocalDate dateNaissance;

    @Getter
    @Setter
    @NotNull
    @Min(0)
    @Column(name = "nbPoint", nullable = false)
    private Integer nbPoint;

    @Getter
    @Setter
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "poste", nullable = false, length = 15)
    private Poste poste;

    @Getter
    @Setter
    @NotBlank
    @Length(min = 3, max = 50)
    @Column(name = "nom", nullable = false, length = 50)
    private String nom;
}