package net.ent.etrs.poeleague.models.entities;

import lombok.*;
import net.ent.etrs.poeleague.common.utils.validator.ValidException;
import net.ent.etrs.poeleague.models.entities.comparator.ChallengeRewardPointsComparator;
import net.ent.etrs.poeleague.models.entities.references.ConstantMetier;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "league", uniqueConstraints = @UniqueConstraint(name = "UK_league_nom", columnNames = "nom"))
@EqualsAndHashCode(of = "nom", callSuper = false)
@ToString(of = {"nom", "dateDebut", "dateFin"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class League extends AbstractEntity {

    @Getter
    @Setter
    @NotBlank
    @Length(min = 5, max = 200)
    @Column(name = "nom", nullable = false, length = 200)
    private String nom;

    @Getter
    @Setter
    @NotNull
    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Getter
    @Setter
    @Column(name = "date_fin", nullable = true)
    private LocalDate dateFin;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "league_classement", joinColumns = @JoinColumn(name = "league_id", foreignKey = @ForeignKey(name = "personnage_league___league_ID___FK")))
    @MapKeyJoinColumn(name = "personnage_id", nullable = false, foreignKey = @ForeignKey(name = "personnage_league___personnage_id___FK"))
    @Column(name = "classement", nullable = false)
    private Map<Personnage, Integer> classement = new HashMap<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id", foreignKey = @ForeignKey(name = "challenge__league_id__FK"))
    private Set<Challenge> lesChallenges = new TreeSet<>(new ChallengeRewardPointsComparator());

    public void ajouterChallenge(Challenge challenge) throws ValidException {
        if (Objects.isNull(challenge)) {
            throw new ValidException("le challenge ne doit pas être null");
        }
        this.lesChallenges.add(challenge);
    }

    public Set<Challenge> getLesChallenges() {
        return Collections.unmodifiableSet(this.lesChallenges);
    }

    /**
     * Permet de savoir si une League est valide
     *
     * @return si la league est valide
     */
    public boolean estValide() {
        return !getLesChallenges().isEmpty()
                && getLesChallenges().size() >= ConstantMetier.NB_MIN_CHALLENGE
                && ContientMinRewardPoints();
    }

    private boolean ContientMinRewardPoints() {
        return getLesChallenges().stream().mapToInt(Challenge::getRewardPoints).sum() >= ConstantMetier.NB_MIN_REWARD_PTS;
    }

    public void ajouterPersonnage(Personnage personnage, Integer classement) throws ValidException {
        if (Objects.isNull(personnage) || Objects.isNull(classement)) {
            throw new ValidException("Le personnage est invalide");
        }
        this.classement.put(personnage, classement);
    }

    public Map<Personnage, Integer> getClassement() {
        return Collections.unmodifiableMap(this.classement);
    }
}
