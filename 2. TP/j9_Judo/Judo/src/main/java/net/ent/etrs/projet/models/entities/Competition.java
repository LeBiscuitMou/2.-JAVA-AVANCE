package net.ent.etrs.projet.models.entities;

import lombok.*;
import net.ent.etrs.projet.models.references.Ville;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "competition", uniqueConstraints = @UniqueConstraint(columnNames = {"nom"}, name = "uk__competition__nom"))
@EqualsAndHashCode(of = {"nom", "annee", "ville"}, callSuper = false)
@ToString(of = {"nom", "annee", "ville"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Competition extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    @NonNull
    //BV
    @Positive(message = "l'annee doit être positif")
    //JPA
    @Column(name = "annee", nullable = false, unique = false)
    private Integer annee;
    
    //LBK
    @Getter
    @Setter
    @NonNull
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(min = 1, max = 50, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 50, nullable = false, unique = false)
    private String nom;

    //LBK
    @Getter
    @Setter
    @NonNull
    //BV
    @NotNull(message = "la ville doit être référencé")
    //JPA
    @Column(name = "ville", length = 50, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private Ville ville;
}