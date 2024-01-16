package net.ent.etrs.hockey.model.entities;

import lombok.*;
import net.ent.etrs.hockey.model.commons.validator.ValidException;
import net.ent.etrs.hockey.model.entities.comparator.EquipePointComparator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "championnat",
        uniqueConstraints = {
                @UniqueConstraint(name = "championnat__nom__anneeChampionnat__UK", columnNames = {"nom", "anneeChampionnat"})
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"nom", "anneeChampionnat"}, callSuper = false)
@ToString(of = {"nom", "anneeChampionnat"}, callSuper = true)
public class Championnat extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le nom championnat doit être référencé")
    @NotBlank(message = "le nom championnat ne doit pas être vide")
    @Size(min = 2, max = 50, message = "la taille du nom championnat n'est pas valide")
    //JPA
    @Column(name = "nomChampionnat", length = 50, nullable = false, unique = false)
    private String nomChampionnat;
    
    //LBK
    @Getter
    @Setter
    //BV
    @Positive(message = "l'annee championnat doit être positif")
    //JPA
    @Column(name = "anneeChampionnat", nullable = false, unique = true)
    private Integer anneeChampionnat;

    //JPA
    @OneToMany()
    @JoinColumn(name = "championnat_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk__equipe__championnat_id"))
    private Set<Equipe> classement = new TreeSet<>(new EquipePointComparator());

    //JPA
    @OneToMany()
    @JoinColumn(name = "championnat_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk__match__championnat_id"))
    private Set<Match> lesMatchs = new HashSet<>();

    public Set<Equipe> getclassement() {
        return Collections.unmodifiableSet(this.classement);
    }

    public void ajouterEquipe(Equipe addEquipe) throws ValidException {
        if(null == addEquipe){
            throw new ValidException(Equipe.class.getSimpleName() + " doit être référencé");
        }
        if(this.classement.contains(addEquipe)){
            throw new ValidException(Equipe.class.getSimpleName() + " : existe déjà dans la liste des Equipe du Championnat");
        }
        this.classement.add(addEquipe);
    }

    public void supprimerEquipe(Equipe delEquipe) throws ValidException {
        if(null == delEquipe){
            throw new ValidException(Equipe.class.getSimpleName() + " doit être référencé");
        }
        if(!this.classement.contains(delEquipe)){
            throw new ValidException(Equipe.class.getSimpleName() + " : n'existe pas dans la liste des Equipe du Championnat");
        }
        this.classement.remove(delEquipe);
    }

    public Set<Match> getlesMatchs() {
        return Collections.unmodifiableSet(this.lesMatchs);
    }

    public void ajouterMatch(Match addMatch) throws ValidException {
        if(null == addMatch){
            throw new ValidException(Match.class.getSimpleName() + " doit être référencé");
        }
        if(this.lesMatchs.contains(addMatch)){
            throw new ValidException(Match.class.getSimpleName() + " : existe déjà dans la liste des Match du Championnat");
        }
        this.lesMatchs.add(addMatch);
    }

    public void supprimerMatch(Match delMatch) throws ValidException {
        if(null == delMatch){
            throw new ValidException(Match.class.getSimpleName() + " doit être référencé");
        }
        if(!this.lesMatchs.contains(delMatch)){
            throw new ValidException(Match.class.getSimpleName() + " : n'existe pas dans la liste des Match du Championnat");
        }
        this.lesMatchs.remove(delMatch);
    }
}