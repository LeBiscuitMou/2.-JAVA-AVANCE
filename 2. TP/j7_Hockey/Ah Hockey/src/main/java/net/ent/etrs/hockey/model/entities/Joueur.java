package net.ent.etrs.hockey.model.entities;

import lombok.*;
import net.ent.etrs.hockey.model.entities.references.Poste;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "joueur",
        uniqueConstraints = {
                @UniqueConstraint(name = "joueur__nom__prenom__date_naissance__UK", columnNames = {"nom", "prenom", "dateNaissance"})
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"nom", "prenom", "dateNaissance"}, callSuper = false)
@ToString(of = {"nom", "prenom", "dateNaissance", "nbPoint", "poste"}, callSuper = true)
public class Joueur extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le prenom doit être référencé")
    @NotBlank(message = "le prenom ne doit pas être vide")
    @Size(min = 1, max = 30, message = "la taille du prenom n'est pas valide")
    //JPA
    @Column(name = "prenom", length = 30, nullable = false, unique = true)
    private String prenom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la date de naissance doit être référencé")
    @Past(message = "la date de naissance doit être dans le passé")
    //JPA
    @Column(name = "dateNaissance", nullable = false, unique = true)
    private LocalDate dateNaissance;

    //LBK
    @Getter
    @Setter
    //BV
    @Positive(message = "le nb point doit être positif")
    //JPA
    @Column(name = "nbPoint", nullable = false, unique = false)
    private Integer nbPoint;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le poste doit être référencé")
    //JPA
    @Column(name = "poste", length = 15, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private Poste poste;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(min = 1, max = 50, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 50, nullable = false, unique = true)
    private String nom;
}