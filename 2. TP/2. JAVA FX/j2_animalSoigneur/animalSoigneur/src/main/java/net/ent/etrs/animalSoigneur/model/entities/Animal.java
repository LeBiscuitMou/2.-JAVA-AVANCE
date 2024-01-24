package net.ent.etrs.animalSoigneur.model.entities;

import lombok.*;
import net.ent.etrs.animalSoigneur.model.entities.references.Type;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "ANIMAL", uniqueConstraints = @UniqueConstraint(columnNames = {"nom"}, name = "uk__animal__nom"))
@EqualsAndHashCode(of = {"nom"}, callSuper = false)
@ToString(of = {"nom", "poids", "taille", "dangerous", "dateNaissance", "type"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Animal extends AbstractEntity {
    //LBK
    @Getter
    @Setter
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
    //BV
    @Positive(message = "le poids doit être positif")
    //JPA
    @Column(name = "poids", nullable = false, unique = false)
    private Integer poids;

    //LBK
    @Getter
    @Setter
    //BV
    @Positive(message = "la taille doit être positif")
    //JPA
    @Column(name = "taille", nullable = false, unique = false)
    private Float taille;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la dangerous doit être référencé")
    //JPA
    @Column(name = "dangerous", length = 5, nullable = false, unique = false)
    private Boolean dangerous;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la date naissance doit être référencé")
    @Past(message = "la date naissance doit être dans le passé")
    //JPA
    @Column(name = "date_naissance", nullable = false, unique = false)
    private LocalDate dateNaissance;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le type doit être référencé")
    //JPA
    @Column(name = "type", length = 50, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private Type type;
}