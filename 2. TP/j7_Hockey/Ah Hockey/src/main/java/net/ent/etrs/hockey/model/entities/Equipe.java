package net.ent.etrs.hockey.model.entities;

import lombok.*;
import net.ent.etrs.hockey.model.commons.validator.ValidException;
import net.ent.etrs.hockey.model.entities.references.Poste;
import net.ent.etrs.hockey.model.entities.references.Ville;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "equipe",
        uniqueConstraints = {
                @UniqueConstraint(name = "equipe__nom__UK", columnNames = {"nom", "ville"})
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"nom", "ville"}, callSuper = false)
@ToString(of = {"nom", "ville"}, callSuper = true)
public class Equipe extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(min = 3, max = 100, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 100, nullable = false, unique = true)
    private String nom;
    
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la ville doit être référencé")
    //JPA
    @Column(name = "ville", length = 50, nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    private Ville ville;
    
    //JPA
    @OneToMany()
    @JoinColumn(name = "equipe_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk__joueur__equipe_id"))
    private Set<Joueur> listeJoueurs = new HashSet<>();

    public Set<Joueur> getListeJoueurs() {
        return Collections.unmodifiableSet(this.listeJoueurs);
    }
    
    public void ajouterJoueur(Joueur addJoueur) throws ValidException {
        if(null == addJoueur){
            throw new ValidException(Joueur.class.getSimpleName() + " doit être référencé");
        }
        if(this.listeJoueurs.contains(addJoueur)){
            throw new ValidException(Joueur.class.getSimpleName() + " : existe déjà dans la liste des Joueur du Equipe");
        }
        this.listeJoueurs.add(addJoueur);
    }
    
    public void supprimerJoueur(Joueur delJoueur) throws ValidException {
        if(null == delJoueur){
            throw new ValidException(Joueur.class.getSimpleName() + " doit être référencé");
        }
        if(!this.listeJoueurs.contains(delJoueur)){
            throw new ValidException(Joueur.class.getSimpleName() + " : n'existe pas dans la liste des Joueur du Equipe");
        }
        this.listeJoueurs.remove(delJoueur);
    }

    public boolean estValide() {
        return !getListeJoueurs().isEmpty() && getListeJoueurs().size() >= 11 && contientMinJoueurPoste();
    }

    private boolean contientMinJoueurPoste() {
        for(Poste poste : Poste.values()){
            if (contientMinimumPosteJoueur(poste)) {
                return true;
            }
        }
        return false;
    }

    private boolean contientMinimumPosteJoueur(Poste poste) {
        if (getListeJoueurs().stream().filter(joueur -> joueur.getPoste().equals(poste)).count() < poste.getMinJoueur()) {
            return false;
        }
        return true;
    }
}