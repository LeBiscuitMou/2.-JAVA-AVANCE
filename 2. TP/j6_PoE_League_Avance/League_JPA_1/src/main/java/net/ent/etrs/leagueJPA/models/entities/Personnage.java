package net.ent.etrs.leagueJPA.models.entities;

import lombok.*;
import net.ent.etrs.leagueJPA.models.entities.references.LabySpecialite;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "personnage", uniqueConstraints = @UniqueConstraint(columnNames = {"pseudo"}, name = "uk__personnage_pseudo"))
@ToString(of = {"level", "build", "pseudo"}, callSuper = true)
@EqualsAndHashCode(of = {"pseudo"}, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Personnage extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le level est null")
    @Positive(message = "le level n'est pas positif")
    @Max(value = 100, message = "le level ne peut pas dépasser 100")
    //JPA
    private Integer level;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le build est null")
    //JPA
    @Column(name = "build_specialite", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private LabySpecialite build;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    //BV
    @NotNull(message = "le pseudo est null")
    @NotBlank(message = "le pseudo est vide")
    @Size(max = 100, min = 1)
    //JPA
    @Column(name = "pseudo", nullable = false, unique = true, length = 100)
    private String pseudo;
}