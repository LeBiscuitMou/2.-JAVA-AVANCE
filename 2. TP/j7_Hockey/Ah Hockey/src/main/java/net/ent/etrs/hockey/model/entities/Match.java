package net.ent.etrs.hockey.model.entities;

import lombok.*;
import net.ent.etrs.hockey.model.commons.validator.ValidException;
import net.ent.etrs.hockey.model.entities.references.Ville;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "match",
        uniqueConstraints = {
                @UniqueConstraint(name = "match__dateMatch__ville__UK", columnNames = {"dateMatch", "ville"})
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"dateMatch", "ville"}, callSuper = false)
@ToString(of = {"dateMatch", "ville"}, callSuper = true)
public class Match extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la ville doit être référencé")
    //JPA
    @Column(name = "ville", length = 50, nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    private Ville ville;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la date du match doit être référencé")
    @PastOrPresent(message = "la date du match ne doit pas être dans le futur")
    //JPA
    @Column(name = "dateMatch", nullable = false, unique = true)
    private LocalDate dateMatch;

    @ElementCollection()
    @CollectionTable(name="match_joueur",
          joinColumns=@JoinColumn(name = "match_id", foreignKey = @ForeignKey(name = "fk__match_joueur__match_id")))
    @MapKeyJoinColumn(name="joueur_id", nullable = false, foreignKey =  @ForeignKey(name = "fk__match_joueur__joueur_id"))
    @Column(name="score", nullable = false, unique = false)
    private Map<Joueur, Integer> score = new HashMap<>();

    public Map<Joueur, Integer> getScore() {
        return Collections.unmodifiableMap(score);
    }

    public void ajouterJoueur(Joueur joueur, Integer but) throws ValidException {
        if(Objects.isNull(joueur) || Objects.isNull(but)){
            throw new ValidException("Le joueur ou les but ne peuvent pas etre null");
        }
        int butMarque = but;
        if(this.score.containsKey(joueur)){
            butMarque = this.score.get(joueur) + but;
        }
        this.score.put(joueur,butMarque);
    }
}