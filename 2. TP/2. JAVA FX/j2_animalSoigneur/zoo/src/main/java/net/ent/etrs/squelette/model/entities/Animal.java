package net.ent.etrs.squelette.model.entities;

import lombok.*;
import net.ent.etrs.squelette.model.references.Type;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "ANIMAL")
@EqualsAndHashCode(of = {"nom"}, callSuper = false)
@ToString(of = {"nom", "poids", "taille", "dangerous", "dateNaissance", "type"}, callSuper = true)
public class Animal extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    @NotBlank
    //JPA
    @Column(name = "nom", length = 255, nullable = false, unique = false)
    private String nom;

    //LBK
    @Getter
    @Setter
    @NotNull
    //BV
    @Positive(message = "le poids doit être positif")
    //JPA
    @Column(name = "poids", nullable = false, unique = false)
    private Integer poids;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull
    @Positive(message = "la taille doit être positif")
    //JPA
    @Column(name = "taille", nullable = false, unique = false)
    private Float taille;

    //LBK
    @Getter
    @Setter
    @NotNull
    //JPA
    @Column(name = "dangereux", nullable = false, unique = false)
    private Boolean dangerous;

    //LBK
    @Getter
    @Setter
    //JPA
    @NotNull
    @PastOrPresent
    @Column(name = "dateNaissance", nullable = false, unique = false)
    private LocalDate dateNaissance;

    //LBK
    @Getter
    @Setter
    //JPA
    @NotNull
    @Column(name = "type", length = 50, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private Type type;
}