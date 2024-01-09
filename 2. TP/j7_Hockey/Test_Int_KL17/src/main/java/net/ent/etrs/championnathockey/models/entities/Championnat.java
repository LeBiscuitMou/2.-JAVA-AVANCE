package net.ent.etrs.championnathockey.models.entities;

import lombok.*;
import net.ent.etrs.championnathockey.commons.validator.ValidException;
import net.ent.etrs.championnathockey.models.entities.comparator.EquipePointComparator;
import net.ent.etrs.championnathockey.models.entities.references.ConstanteMetier;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.*;

@Entity
@Table(name = "CHAMPIONNAT", uniqueConstraints = @UniqueConstraint(name = "CHAPIONNAT___NOM_CAMPIONNAT__ANNEE_CHAMPIONNAT___UK", columnNames = {"ANNEE_CHAMPIONNAT", "NOM_CHAMPIONNAT"}))

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"anneeChampionnat", "nomChampionnat"})
@ToString(callSuper = true, of = {"anneeChampionnat", "nomChampionnat"})
public class Championnat extends AbstractEntity {

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = ConstanteMetier.CHAMPIONNAT_METIER_ANNEE_NULL)
    @PositiveOrZero(message = ConstanteMetier.CHAMPIONNAT_METIER_ANNEE_POSITIVE)
    //JPA
    @Column(name = "ANNEE_CHAMPIONNAT", nullable = false)
    private Integer anneeChampionnat;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = ConstanteMetier.CHAMPIONNAT_METIER_NOM_NULL)
    @NotBlank(message = ConstanteMetier.CHAMPIONNAT_METIER_NOM_VIDE)
    @Length(min = 2, max = 50, message = ConstanteMetier.CHAMPIONNAT_METIER_NOM_LONGUEUR)
    //JPA
    @Column(name = "NOM_CHAMPIONNAT", nullable = false, length = 50)
    private String nomChampionnat;

    //JPA
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHAMPIONNAT_ID", foreignKey = @ForeignKey(name = "EQUIPE__CHAMPIONNAT_ID__FK"))
    private Set<Equipe> classement = new TreeSet<>(new EquipePointComparator());
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHAMPIONNAT_ID", foreignKey = @ForeignKey(name = "MATCH__CHAMPIONNAT_ID__FK"))
    private Set<Match> lesMatchs = new HashSet<>();

    public void ajouterEquipe(final Equipe equipe) throws ValidException {
        if (Objects.isNull(equipe)) {
            throw new ValidException("l'equipe ne doit pas être null");
        }
        if (!equipe.estValide()) {
            throw new ValidException("l'equipe doit avoir le minimum de joueur pour être ajouter au championnat");
        }
        this.classement.add(equipe);
    }

    public void supprimerEquipe(final Equipe equipe) throws ValidException {
        if (Objects.isNull(equipe)) {
            throw new ValidException("l'equipe ne doit pas être null");
        }
        this.classement.remove(equipe);
    }

    public Set<Equipe> getListeEquipes() {
        return Collections.unmodifiableSet(this.classement);
    }

    public void ajouterMatch(final Match match) throws ValidException {
        if (Objects.isNull(match)) {
            throw new ValidException("le match ne doit pas être null");
        }
        this.lesMatchs.add(match);
    }

    public void supprimerMatch(final Match match) throws ValidException {
        if (Objects.isNull(match)) {
            throw new ValidException("le match ne doit pas être null");
        }
        this.lesMatchs.remove(match);
    }

    public Set<Match> getListeMatch() {
        return Collections.unmodifiableSet(this.lesMatchs);
    }
}
