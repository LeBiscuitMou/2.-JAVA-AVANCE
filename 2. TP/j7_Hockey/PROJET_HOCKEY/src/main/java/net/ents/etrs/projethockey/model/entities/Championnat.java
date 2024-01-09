package net.ents.etrs.projethockey.model.entities;


import lombok.*;
import net.ents.etrs.projethockey.model.entities.comparator.JoueurRewardPointsComparator;
import net.ents.etrs.projethockey.model.entities.execptions.ChampionnatException;
import net.ents.etrs.projethockey.model.entities.references.ConstantesMetier;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.*;

@Entity
@EqualsAndHashCode(of = {"nomChampionnat","anneeChampionnat"}, callSuper = false)
@Table(name = "championnat", uniqueConstraints = @UniqueConstraint(name = "UK_championnat_nom_championnat_annee_championnat", columnNames = "nom_championnat_annee_championnat"))
@ToString(of = {"nomChampionnat","anneeChampionnat"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Championnat extends AbstractEntity {

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "championnat_id", foreignKey = @ForeignKey(name = "classement__championnat_id__FK"))
    private Set<Equipe> classement=new TreeSet<>(new JoueurRewardPointsComparator());


    @Getter
    @Setter
    @Positive
    @Column(name = "annee_championnat", nullable = true,unique = true)
    private Integer anneeChampionnat;

    @Getter
    @Setter
    @NotBlank
    @Length(min = 2, max = 50,message = "le nom du championnat doit être entre 2 et 50 caractères")
    @Column(name = "nom_championnat", nullable = false, length = 50,unique = true)
    private String nomChampionnat;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "championnat_id", foreignKey = @ForeignKey(name = "match__championnat_id__FK"))
    private Set<Match> lesMatchs = new HashSet<>();

    //GETTER

    public Set<Match> getLesMatchs() {
        return Collections.unmodifiableSet(lesMatchs);
    }



    /* ******************************** FONCTIONS ******************************** */

    public void supprimerEquipe(Equipe equipe) throws ChampionnatException {
if(Objects.isNull(equipe)) {
    throw new ChampionnatException(ConstantesMetier.ERR_EQUIPE_NULL);
}

   classement.remove(equipe);


    }

    public void ajouterMatch(Match match) throws ChampionnatException {
        if(Objects.isNull(match)) {
            throw new ChampionnatException(ConstantesMetier.ERR_MATCH_NULL);
        }
        lesMatchs.add(match);

    }

    public void supprimerMatch(Match match) throws ChampionnatException {
        if(Objects.isNull(match)) {
            throw new ChampionnatException(ConstantesMetier.ERR_MATCH_NULL);
        }
        lesMatchs.remove(match);
    }

    public void ajouterEquipe(Equipe equipe) throws ChampionnatException {
        if(Objects.isNull(equipe)) {
            throw new ChampionnatException(ConstantesMetier.ERR_EQUIPE_NULL);
        }
        classement.add(equipe);
    }

}
