package net.ent.etrs.poeleague.models.entities;

import lombok.*;
import net.ent.etrs.poeleague.models.entities.references.LabySpecialite;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "personnage", uniqueConstraints = @UniqueConstraint(name = "UK_personnage_pseudo", columnNames = "pseudo"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "pseudo", callSuper = false)
@ToString(of = {"pseudo", "level", "build"}, callSuper = true)
public class Personnage extends AbstractEntity {

    @Setter
    @NotBlank
    @Length(min = 1, max = 100)
    @Column(name = "pseudo", nullable = false, length = 100)
    private String pseudo;

    @Getter
    @Setter
    @Positive
    @Max(100)
    @NotNull
    @Column(name = "level", nullable = false)
    private Integer level;

    @Getter
    @Setter
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "build", nullable = false)
    private LabySpecialite build;
}
