package net.ent.etrs.poeleague.models.entities;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "challenge", uniqueConstraints = @UniqueConstraint(name = "UK_challenge_nom", columnNames = "nom"))
@EqualsAndHashCode(of = "nom", callSuper = false)
@ToString(of = {"nom", "desc", "rewardPoints"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge extends AbstractEntity {

    @Getter
    @Setter
    @NotBlank
    @Length(min = 1, max = 100)
    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Getter
    @Setter
    @NotBlank
    @Length(max = 255)
    @Column(name = "description", nullable = true, length = 255)
    private String desc;

    @Getter
    @Setter
    @NotNull
    @Column(name = "rewardPoints", nullable = false)
    private Integer rewardPoints;
}
