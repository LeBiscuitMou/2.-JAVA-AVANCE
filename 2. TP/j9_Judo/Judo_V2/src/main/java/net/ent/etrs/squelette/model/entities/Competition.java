package net.ent.etrs.squelette.model.entities;

import lombok.*;
import net.ent.etrs.squelette.model.entities.references.Ville;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "competition")
@EqualsAndHashCode(of = {"nom", "annee", "ville"}, callSuper = false)
@ToString(of = {"nom", "annee", "ville"}, callSuper = true)
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Competition extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    @NonNull
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(max = 50, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 50, nullable = false, unique = false)
    private String nom;

    //LBK
    @Getter
    @Setter
    @NonNull
    //BV
    @Positive(message = "l' annee doit être positif")
    //JPA
    @Column(name = "annee", nullable = false, unique = false)
    private Integer annee;

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