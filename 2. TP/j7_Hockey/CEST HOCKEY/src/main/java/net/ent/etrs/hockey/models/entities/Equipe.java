package net.ent.etrs.hockey.models.entities;

import lombok.*;
import net.ent.etrs.hockey.common.utils.validator.ValidException;
import net.ent.etrs.hockey.models.entities.references.Poste;
import net.ent.etrs.hockey.models.entities.references.Ville;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "equipe", uniqueConstraints = @UniqueConstraint(name = "UK_equipe_nom_ville", columnNames = {"nom", "ville"}))
@EqualsAndHashCode(of = {"nom", "ville"}, callSuper = false)
@ToString(of = {"nom", "ville"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Equipe extends AbstractEntity {
    @Getter
    @Setter
    @NotBlank
    @Length(min = 3, max = 100)
    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Getter
    @Setter
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "ville", nullable = false)
    private Ville ville;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipe_id", foreignKey = @ForeignKey(name = "joueur__equipe_id__FK"))
    private Set<Joueur> listeJoueurs = new TreeSet<>();

    public Set<Joueur> getListeJoueurs() {
        return Collections.unmodifiableSet(listeJoueurs);
    }

    private boolean contientMinimumPosteJoueur(Poste poste) {
        if (poste.equals(Poste.GARDIEN) && poste.getMinJoueur().equals(1)) {
            return true;
        }
        if (poste.equals(Poste.DEFENSEUR) && poste.getMinJoueur().equals(4)) {
            return true;
        }
        if (poste.equals(Poste.ATTAQUANT) && poste.getMinJoueur().equals(6)) {
            return true;
        }
        return false;
    }

    private boolean contientMinJoueurPoste() {

    }

    public boolean estValide() {
        return !getListeJoueurs().isEmpty()
                && contientMinJoueurPoste();
    }

    public void ajouterJoueur(Joueur joueur) throws ValidException {
        if (Objects.isNull(joueur)) {
            throw new ValidException("Le joueur ne doit pas être null");
        }
        if (listeJoueurs.contains(joueur)) {
            throw new ValidException("Le joueur existe déjà dans la liste");
        }
        listeJoueurs.add(joueur);
    }

    public void supprimerJoueur(Joueur joueur) throws ValidException {
        if (Objects.isNull(joueur)) {
            throw new ValidException("Le joueur ne doit pas être null");
        }
        if (!listeJoueurs.contains(joueur)) {
            throw new ValidException("Le joueur n'existe pas dans la liste");
        }
        listeJoueurs.remove(joueur);
    }

    public int getTotalPoints() {
        return listeJoueurs.stream().mapToInt(Joueur::getNbPoint).sum();
    }
}