package net.ent.etrs.judo.models.entities;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.*;
import lombok.ToString;
import net.ent.etrs.judo.models.entities.references.Ville;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.*;

@Entity
@Table(name = "competition", uniqueConstraints = @UniqueConstraint(columnNames = {"nom", "annee", "ville"}, name = "uk__competition"))
@EqualsAndHashCode(of = {"nom", "annee", "ville"}, callSuper = false)
@ToString(of = {"nom", "annee", "ville"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Competition extends AbstractEntity {
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
    @Positive(message = "l'annee doit être positif")
    //JPA
    @Column(name = "annee", nullable = false, unique = false)
    private int annee;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la ville doit être référencé")
    //JPA
    @Column(name = "ville", length = 50, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private Ville ville;

    public Competition(String nom, int annee, Ville ville) {
        this.setNom(nom);
        this.setVille(ville);
        this.setAnnee(annee);
    }
}
