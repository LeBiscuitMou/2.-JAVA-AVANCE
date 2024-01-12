package net.ent.etrs.judo.models.entities;

import lombok.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.ent.etrs.judo.models.entities.references.Ville;
import net.ent.etrs.judo.models.exceptions.ClubException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "club", uniqueConstraints = @UniqueConstraint(columnNames = {"nom"}, name = "uk__club"))
@EqualsAndHashCode(of = {"nom"}, callSuper = false)
@ToString(of = {"nom", "ville"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Club extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(min = 1, max = 50, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 50, nullable = false, unique = true)
    private String nom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la Ville doit être référencé")
    //JPA
    @Column(name = "ville", length = 50, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private Ville ville;

    //JPA
    @OneToMany()
    @JoinColumn(name = "club_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk__judoka__club_id"))
    private final List<Judoka> membres = new ArrayList<>();
    
    public void ajouterJudoka(Judoka addJudoka) throws ClubException {
        if(null == addJudoka){
            throw new ClubException(Judoka.class.getSimpleName() + " doit être référencé");
        }
        if(this.membres.contains(addJudoka)){
            throw new ClubException(Judoka.class.getSimpleName() + " : existe déjà dans la liste des Judoka du Club");
        }
        this.membres.add(addJudoka);
    }
    
    public void supprimerJoueur(Judoka delJudoka) throws ClubException {
        if(null == delJudoka){
            throw new ClubException(Judoka.class.getSimpleName() + " doit être référencé");
        }
        if(!this.membres.contains(delJudoka)){
            throw new ClubException(Judoka.class.getSimpleName() + " : n'existe pas dans la liste des Judoka du Club");
        }
        this.membres.remove(delJudoka);
    }


}
