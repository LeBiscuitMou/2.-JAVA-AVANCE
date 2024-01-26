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
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "soigneur", uniqueConstraints = @UniqueConstraint(name = "animal___nom__prenom__date_de_naissance___uk", columnNames = {"nom", "prenom", "date_de_naissance"}))
@EqualsAndHashCode(of = {"nom", "prenom", "dateDeNaissance"}, callSuper = false)
@ToString(of = {"nom", "prenom", "dateDeNaissance", "dateArrivee"}, callSuper = true)
public class Soigneur extends AbstractEntity {

    @NotBlank
    @Length(max = 50)
    @Getter @Setter
    @Column(name = "nom", length = 50, nullable = false)
    private String nom;

    @NotBlank
    @Length(max = 50)
    @Getter @Setter
    @Column(name = "prenom", length = 50, nullable = false)
    private String prenom;

    @NotNull
    @PastOrPresent
    @Getter @Setter
    @Column(name = "date_de_naissance", nullable = false)
    private LocalDate dateDeNaissance;

    @NotNull
    @PastOrPresent
    @Getter @Setter
    @Column(name = "date_arrivee", nullable = false)
    private LocalDate dateArrivee;

    @Getter @Setter
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "soigneur_type", joinColumns = @JoinColumn(name = "soigneur_id", foreignKey = @ForeignKey(name = "soigneur_type__soigneur_id__fk")))
    @Column(name = "type_soignable", length = 20, nullable = false)
    private Set<Type> typesSoignable = new HashSet<>();

    @Getter @Setter
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "soigneur_id", foreignKey = @ForeignKey(name = "animal__soigneur_id__fk"))
    private Set<Animal> animaux = new HashSet<>();
}
