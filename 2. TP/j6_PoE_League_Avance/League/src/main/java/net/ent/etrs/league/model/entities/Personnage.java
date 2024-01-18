package net.ent.etrs.league.model.entities;

import lombok.*;
import net.ent.etrs.league.model.entities.references.LabySpecialite;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "personnage", uniqueConstraints = @UniqueConstraint(columnNames = {"pseudo"}, name = "uk__personnage__pseudo"))
@EqualsAndHashCode(of = {"pseudo"}, callSuper = false)
@ToString(of = {"pseudo", "level", "build"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Personnage extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le pseudo doit être référencé")
    @NotBlank(message = "le pseudo ne doit pas être vide")
    @Size(min = 1, max = 100, message = "la taille du pseudo n'est pas valide")
    //JPA
    @Column(name = "pseudo", length = 100, nullable = false, unique = true)
    private String pseudo;

    //LBK
    @Getter   @Setter
    //BV
    @Positive(message = "le level doit être positif")
    @Max(value = 100, message = "le level ne peut pas être au dessus de 100")
    //JPA
    @Column(name = "level", nullable = false)
    private Integer level;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le build doit être référencé")
    //JPA
    @Column(name = "build", length = 50, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private LabySpecialite build;
}