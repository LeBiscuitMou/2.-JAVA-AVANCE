package net.ent.etrs.garage.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import net.ent.etrs.garage.model.entities.references.Nationalite;

@Entity
@Table(name = "marque", uniqueConstraints = @UniqueConstraint(columnNames = {"libelle"}, name = "uk__marque__libelle"))
@EqualsAndHashCode(of = {"libelle"}, callSuper = false)
@ToString(of = {"libelle", "cheminComplet", "nationalite"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Marque extends AbstractEntity {
    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "le libelle doit être référencé")
    @NotBlank(message = "le libelle ne doit pas être vide")
    @Size(min = 2, message = "la taille du libelle n'est pas valide")
    //JPA
    @Column(name = "libelle", length = 255, nullable = false, unique = false)
    private String libelle;

    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "le chemin complet doit être référencé")
    //JPA
    @Column(name = "chemin_complet", length = 255, nullable = false, unique = false)
    private String cheminComplet;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la nationalite doit être référencé")
    //JPA
    @Column(name = "nationalite", length = 50, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private Nationalite nationalite;
}