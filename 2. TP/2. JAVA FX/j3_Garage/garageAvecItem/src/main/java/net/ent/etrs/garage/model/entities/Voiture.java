package net.ent.etrs.garage.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "voiture", uniqueConstraints = @UniqueConstraint(columnNames = {"immatriculation"}, name = "uk__voiture__immatriculation"))
@EqualsAndHashCode(of = {"immatriculation"}, callSuper = false)
@ToString(of = {"immatriculation", "modele", "puissance", "miseEnCirculation", "marque"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Voiture extends AbstractEntity {
    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "l' immatriculation doit être référencé")
    @NotBlank(message = "l' immatriculation ne doit pas être vide")
    @Size(min = 2, message = "la taille du immatriculation n'est pas valide")
    @Pattern(regexp = "[A-Z]{2}-\\d{3}-[A-Z]{2}")
    //JPA
    @Column(name = "immatriculation", length = 50, nullable = false, unique = false)
    private String immatriculation;

    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "le modele doit être référencé")
    @NotBlank(message = "le modele ne doit pas être vide")
    @Size(min = 2, message = "la taille du modele n'est pas valide")
    //JPA
    @Column(name = "modele", length = 255, nullable = false, unique = false)
    private String modele;

    //LBK
    @Getter   @Setter
    //BV
    @Positive(message = "la puissance doit être positif")
    //JPA
    @Column(name = "puissance", nullable = false, unique = false)
    private Integer puissance;

    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "la mise en circulation doit être référencé")
    @PastOrPresent(message = "la mise en circulation ne doit pas être dans le futur")
    //JPA
    @Column(name = "mise_en_circulation", nullable = false, unique = false)
    private LocalDate miseEnCirculation;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la marque doit être référencé")
    //JPA
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "marque_id", referencedColumnName = "libelle", foreignKey = @ForeignKey(name = "fk__voiture_marque__marque_id"))
    private Marque marque;
}