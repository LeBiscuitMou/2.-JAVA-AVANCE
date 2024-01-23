package net.ent.etrs.league.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "challenge", uniqueConstraints = @UniqueConstraint(columnNames = {"nom"}, name = "uk__challenge__nom"))
@EqualsAndHashCode(of = {"nom"}, callSuper = false)
@ToString(of = {"nom", "desc", "rewardPoints"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(min = 1, max = 100, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 100, nullable = false, unique = true)
    private String nom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la desc doit être référencé")
    @NotBlank(message = "la desc ne doit pas être vide")
    @Size(max = 255, message = "la taille du desc n'est pas valide")
    //JPA
    @Column(name = "desc", length = 255, nullable = true, unique = false)
    private String desc;

    //LBK
    @Getter
    @Setter
    //BV
    @Positive(message = "les reward points doit être positif")
    //JPA
    @Column(name = "rewardPoints", nullable = false, unique = false)
    private Integer rewardPoints;
}