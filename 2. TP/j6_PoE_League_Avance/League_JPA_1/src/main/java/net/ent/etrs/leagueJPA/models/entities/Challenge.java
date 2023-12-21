package net.ent.etrs.leagueJPA.models.entities;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.*;

@Entity
@Table(name = "challenge", uniqueConstraints = @UniqueConstraint(columnNames = {"nom"}, name = "uk__challenge_nom"))

@EqualsAndHashCode(of = "nom", callSuper = false)
@ToString(of = {"nom", "desc", "rewardPoints"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "les rewards points sont null")
    @PositiveOrZero(message = "Les rewards points sont ^négatifs ou à 0")
    //JPA
    @Column(name = "rewards_points", nullable = false, unique = false)
    private Integer rewardPoints;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la description est null")
    @NotBlank(message = "la description est vide")
    @Size(max = 255)
    //JPA
    @Column(name = "description", nullable = false, unique = true, length = 255)
    private String desc;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le nom est null")
    @NotBlank(message = "le nom est vide")
    @Size(max = 100, min = 1)
    //JPA
    @Column(name = "nom", nullable = false, unique = true, length = 100)
    private String nom;
}