package net.ent.etrs.zoo.models.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.ent.etrs.zoo.models.entities.references.Type;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Table(name = "animal", uniqueConstraints = @UniqueConstraint(name = "animal__nom__uk", columnNames = "nom"))
@EqualsAndHashCode(of = "nom", callSuper = false)
@ToString(of = {"nom", "poids", "taille", "dangerous", "dateDeNaissance", "type"}, callSuper = true)
public class Animal extends AbstractEntity {

    @NotBlank
    @Length(max = 50)
    @Getter @Setter
    @Column(name = "nom", length = 50, nullable = false)
    private String nom;

    @NotNull
    @Positive
    @Getter @Setter
    @Column(name = "poids", nullable = false)
    private Float poids;

    @NotNull
    @Positive
    @Getter @Setter
    @Column(name = "taille", nullable = false)
    private Integer taille;

    @Getter @Setter
    @Column(name = "dangerous", nullable = false)
    private boolean dangerous;

    @NotNull
    @PastOrPresent
    @Getter @Setter
    @Column(name = "date_de_naissance", nullable = false)
    private LocalDate dateDeNaissance;

    @NotNull
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 20, nullable = false)
    private Type type;
}
