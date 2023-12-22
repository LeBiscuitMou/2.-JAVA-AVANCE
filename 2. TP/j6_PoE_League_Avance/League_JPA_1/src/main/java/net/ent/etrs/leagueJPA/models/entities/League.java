package net.ent.etrs.leagueJPA.models.entities;

import lombok.*;
import net.ent.etrs.leagueJPA.models.exceptions.LeagueException;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "league", uniqueConstraints = @UniqueConstraint(columnNames = {"nom"}, name = "uk__league_nom"))

@EqualsAndHashCode(of = "nom", callSuper = false)
@ToString(of = {"nom", "dateDebut", "dateFin"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class League extends AbstractEntity {
    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    //BV
    @NotNull(message = "le nom est null")
    @NotBlank(message = "le nom est vide")
    @Size(min = 5, max = 200)
    //JPA
    @Column(name ="nom",nullable = false,unique = true,length = 200)
    private String nom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la date debut est null")
    //JPA
    @Column(name = "date_debut", nullable = false, unique = false)
    private LocalDate dateDebut;

    //LBK
    @Getter
    @Setter
    //BV
    //JPA
    @Column(name = "date_fin", nullable = true, unique = false)
    private LocalDate dateFin;

    @OneToMany
    @JoinColumn(name = "league_id", foreignKey = @ForeignKey(name = "fk__league_id"))
    private Set<Challenge> challenges = new HashSet<>();

    public Set<Challenge> getChallenges() {
        return Collections.unmodifiableSet(challenges);
    }

    public void ajouterChallenge(Challenge challenge) throws LeagueException {
        if (Objects.isNull(challenge)) {
            throw new LeagueException("Challenge null");
        }
        if (challenges.contains(challenge)) {
            throw new LeagueException("Challenge deja existant");
        }
        challenges.add(challenge);
    }

    @ElementCollection
    @CollectionTable(name = "classement", joinColumns = @JoinColumn(name = "league_id", foreignKey = @ForeignKey(name = "fk__classement_league_id")))
    @MapKeyJoinColumn(name = "personnage", foreignKey = @ForeignKey(name = "fk__classement_personnage_id"))
    @Column(name = "classement")
    private Map<Personnage, Integer> classement = new HashMap<>();

    public Map<Personnage, Integer> getClassement() {
        return Collections.unmodifiableMap(classement);
    }

    public void ajouterPersonnage(Personnage personnage, Integer numero) throws LeagueException {
        if (Objects.isNull(personnage) || Objects.isNull(classement)) {
            throw new LeagueException("Le personnage est invalide");
        }
        this.classement.put(personnage, numero);
    }

    public boolean estValide() {
        return !getChallenges().isEmpty()
                && getChallenges().size() >= 10
                && ContientMinRewardPoints();
    }

    private boolean ContientMinRewardPoints() {
        return this.challenges.stream().mapToInt(Challenge::getRewardPoints).sum() >= 50;
    }
}