package net.ent.etrs.league.model.entities;

import lombok.*;
import net.ent.etrs.league.model.commons.validator.ValidException;
import net.ent.etrs.league.model.entities.comparator.ChallengeComparator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "league", uniqueConstraints = @UniqueConstraint(columnNames = {"nom"}, name = "uk__league__nom"))
@EqualsAndHashCode(of = {"nom"}, callSuper = false)
@ToString(of = {"nom", "dateDebut", "dateFin"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class League extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(min = 5, max = 200, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 200, nullable = false, unique = true)
    private String nom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la date debut doit être référencé")
    //JPA
    @Column(name = "dateDebut", nullable = false, unique = false)
    private LocalDate dateDebut;

    //LBK
    @Getter
    @Setter
    //BV
    //JPA
    @Column(name = "dateFin", nullable = true, unique = false)
    private LocalDate dateFin;

    //JPA
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk__challenge__league_id"))
    private Set<Challenge> lesChallenges = new TreeSet<>(new ChallengeComparator());

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="league_personnage",
          joinColumns=@JoinColumn(name = "league_id", foreignKey = @ForeignKey(name = "fk__league_personnage__league_id")))
    @MapKeyJoinColumn(name="personnage_id", nullable = false, foreignKey =  @ForeignKey(name = "fk__league_personnage__personnage_id"))
    @Column(name="classement", nullable = false, unique = false)
    private Map<Personnage, Integer> classement = new HashMap<>();

    public Map<Personnage, Integer> getClassement() {
        return Collections.unmodifiableMap(this.classement);
    }

    public void ajouterPersonnage(Personnage addPersonnage, Integer classement) throws ValidException {
        if(Objects.isNull(addPersonnage)){
            throw new ValidException(Personnage.class.getSimpleName() + " doit être référencé");
        }
        if(Objects.isNull(classement)){
            throw new ValidException("classement doit être référencé");
        }
        this.classement.put(addPersonnage, classement + this.classement.get(addPersonnage));
    }

    public void ajouterChallenge(Challenge addChallenge) throws ValidException {
        if(Objects.isNull(addChallenge)){
            throw new ValidException(Challenge.class.getSimpleName() + " doit être référencé");
        }
        this.lesChallenges.add(addChallenge);
    }

    private boolean contientMinRewardPoints() {
        Integer minRewardPoints = 50;
        Integer points = 0;
        for(Challenge c : this.lesChallenges){
            points += c.getRewardPoints();
        }
        if (points < minRewardPoints) {
            return false;
        }
        return true;
    }

    public boolean estValide() {
        return this.lesChallenges.size() >= 10 && contientMinRewardPoints();
    }
}