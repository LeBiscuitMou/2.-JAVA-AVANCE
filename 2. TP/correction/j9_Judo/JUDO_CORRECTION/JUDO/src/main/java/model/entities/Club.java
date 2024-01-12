package model.entities;

import lombok.*;
import model.entities.references.Ville;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CLUB",
        uniqueConstraints = {
                @UniqueConstraint(name = "CLUB__NOM__UK", columnNames = "NOM")
        }
)
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@EqualsAndHashCode(of = "nom", callSuper = false)
@ToString(of = {"nom"}, callSuper = true)
public class Club extends AbstractEntity {

    @Getter
    @Size(min = 1)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLUB_ID", foreignKey = @ForeignKey(name = "JUDOKA__CLUB_ID__FK"))
    private final Set<Judoka> membres = new HashSet<>();

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

}
