package net.ents.etrs.projethockey.model.entities;

import lombok.*;
import net.ents.etrs.projethockey.model.entities.execptions.MatchException;
import net.ents.etrs.projethockey.model.entities.references.ConstantesMetier;
import net.ents.etrs.projethockey.model.entities.references.Ville;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "match", uniqueConstraints = @UniqueConstraint(name = "UK_match_ville_date_match", columnNames="ville_date_match"))
@EqualsAndHashCode(of = {"ville","dateMatch"}, callSuper = false)
@ToString(of = {"ville","dateMatch"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Match extends AbstractEntity {

    @Getter
    @Setter
   @Column(name = "ville", nullable = false, length = 50,unique = true)
    private Ville ville;

    @Getter
    @Setter

    @PastOrPresent(message = ConstantesMetier.ERR_MATCH_DATE_MATCH_INVALIDE)
    @Column(name = "date_match",nullable = false,unique = true)
    private LocalDate dateMatch;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "match_score", joinColumns = @JoinColumn(name = "match_id"),foreignKey = @ForeignKey(name = "joueur_match__match_id__FK"))
    @MapKeyColumn(name = "joueur_id",nullable = false)
    @Column(name = "score", nullable = false)
    private Map<Joueur,Integer> score = new HashMap<>();

    public Map<Joueur, Integer> getScore() {
        return Collections.unmodifiableMap(score);
    }

    /* ******************************** FONCTIONS ******************************** */
   public void ajouterBut(Joueur joueur,Integer nbBut) throws MatchException {
       if (Objects.isNull(joueur) || Objects.isNull(nbBut)) {
           throw new MatchException(ConstantesMetier.ERR_JOUEUR_BUT_INEXISTANT);
       }
        if(score.containsKey(joueur)) {
            score.put(joueur,score.get(joueur)+nbBut);
        }else {
            score.put(joueur,nbBut);
        }


   }


}

