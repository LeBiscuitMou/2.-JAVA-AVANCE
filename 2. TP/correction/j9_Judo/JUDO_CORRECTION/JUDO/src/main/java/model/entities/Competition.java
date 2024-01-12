package model.entities;


import lombok.*;
import model.entities.references.Ville;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "COMPETITION",
        uniqueConstraints = @UniqueConstraint(name = "COMPETITION___NOM__ANNEE__VILLE___UK", columnNames = {
                "NOM", "ANNEE", "VILLE"
        })
)
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"nom", "annee", "ville"}, callSuper = false)
@ToString(of = {"nom", "annee", "ville"}, callSuper = true)
public class Competition extends AbstractEntity {

    @Getter
    @Setter
    @NonNull
    @NotBlank
    @Length(max = 50)
    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;

    @Getter
    @Setter
    @NonNull
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "VILLE", nullable = false, length = 50)
    private Ville ville;

    @Getter
    @Setter
    @NonNull
    @NotNull
    @Positive
    @Column(name = "ANNEE", nullable = false)
    private Integer annee;

}
