package net.ent.etrs.championnathockey.models.entities;

import lombok.*;
import net.ent.etrs.championnathockey.commons.validator.ValidException;
import net.ent.etrs.championnathockey.models.entities.references.ConstanteMetier;
import net.ent.etrs.championnathockey.models.entities.references.Ville;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "MATCH",uniqueConstraints = @UniqueConstraint(name = "MATCH___DATE_MATCH__VILLE___UK",columnNames = {"DATE_MATCH","VILLE"}))

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false,of = {"dateMatch","ville"})
@ToString(callSuper = true,of = {"dateMatch","ville"})
public class Match extends AbstractEntity{

    //LBK
    @Getter @Setter
    //BV
    @NotNull(message = ConstanteMetier.MATCH_METIER_DATE_NULL)
    @PastOrPresent(message = ConstanteMetier.MATCH_METIER_DATE_PAST)
    //JPA
    @Column(name = "DATE_MATCH",nullable = false)
    private LocalDate dateMatch;

    //LBK
    @Getter @Setter
    //BV
    @NotNull(message = ConstanteMetier.MATCH_METIER_VILLE_NULL)
    @Length(max = 50, message = ConstanteMetier.MATCH_METIER_VILLE_LENGTH)
    //JPA
    @Column(name = "VILLE",nullable = false, length = 50)
    private Ville ville;

    //JPA
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "JOUEUR_MATCH",joinColumns = @JoinColumn(name = "JOUEUR_ID",foreignKey = @ForeignKey(name = "JOUEUR_MATCH___JOUEUR_ID___FK")))
    @MapKeyJoinColumn(name = "MATCH_ID",nullable = false,foreignKey = @ForeignKey(name = "JOUEUR_MATCH___MATCH_ID___FK"))
    @Column(name = "SCORE",nullable = false)
    private Map<Joueur,Integer> score;

    public void ajouterBut(Joueur joueur,Integer but) throws ValidException {
        if(Objects.isNull(joueur) || Objects.isNull(but)){
            throw new ValidException(ConstanteMetier.MATCH_SCORE_NULL);
        }
        int butMarque = but;
        if(this.score.containsKey(joueur)){
            butMarque = this.score.get(joueur) + but;
        }
        this.score.put(joueur,butMarque);
    }

    public Map<Joueur, Integer> getScore() {
        return Collections.unmodifiableMap(this.score);
    }

}
