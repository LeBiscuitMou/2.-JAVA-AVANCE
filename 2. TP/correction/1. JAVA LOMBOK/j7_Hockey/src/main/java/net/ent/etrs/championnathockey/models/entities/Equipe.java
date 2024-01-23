package net.ent.etrs.championnathockey.models.entities;

import lombok.*;
import net.ent.etrs.championnathockey.commons.validator.ValidException;
import net.ent.etrs.championnathockey.models.entities.references.ConstanteMetier;
import net.ent.etrs.championnathockey.models.entities.references.Poste;
import net.ent.etrs.championnathockey.models.entities.references.Ville;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "EQUIPE", uniqueConstraints = @UniqueConstraint(name = "EQUIPE__NOM_VILLE__UK", columnNames = {"NOM", "VILLE"}))

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"nom", "ville"})
@ToString(callSuper = true, of = {"nom", "ville"})
public class Equipe extends AbstractEntity {

    //LBK
    @Getter
    @Setter
    //BV
    @NotBlank(message = ConstanteMetier.EQUIPE_METIER_NOM_VIDE)
    @NotNull(message = ConstanteMetier.EQUIPE_METIER_NOM_NULL)
    @Length(min = 3, max = 100, message = ConstanteMetier.EQUIPE_METIER_NOM_LONGUEUR)
    //JPA
    @Column(name = "NOM", nullable = false, length = 100)
    private String nom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = ConstanteMetier.EQUIPE_METIER_VILLE_NULL)
    //JPA
    @Enumerated(EnumType.STRING)
    @Column(name = "VILLE", nullable = false, length = 50)
    private Ville ville;

    //BV
   // @Size(min = ConstanteMetier.NB_JOUEUR_MIN_PAR_EQUIPE, message = ConstanteMetier.EQUIPE_METIER_NB_JOUEUR_MIN)
    //JPA
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "EQUIPE_ID", foreignKey = @ForeignKey(name = "JOUEUR__EQUIPE_ID__FK"))
    private Set<Joueur> listeJoueurs = new HashSet<>();

    public void ajouterJoueur(final Joueur joueur) throws ValidException {
        if (Objects.isNull(joueur)) {
            throw new ValidException("le joueur ne doit pas être null");
        }
        this.listeJoueurs.add(joueur);
    }

    public void supprimerJoueur(final Joueur joueur) throws ValidException {
        if (Objects.isNull(joueur)) {
            throw new ValidException("le joueur ne doit pas être null");
        }
        this.listeJoueurs.remove(joueur);
    }

    public Set<Joueur> getListeJoueurs() {
        return Collections.unmodifiableSet(this.listeJoueurs);
    }

    /**
     * Permet de savoir si une equipe est valide pour participer à un championnat.
     *
     * @return
     */
    public boolean estValide() {
        if (getListeJoueurs().isEmpty()
                || getListeJoueurs().size() < ConstanteMetier.NB_JOUEUR_MIN_PAR_EQUIPE
                || !ContientMinJoueurPoste()) {
            return false;
        }
        return true;
    }

    /**
     * Permet de savoir si l'équipe contient le minimum de joueurs pour chaque poste pour participer à un championnat.
     *
     * @return vrai si elle possède le minimum
     */
    private boolean ContientMinJoueurPoste() {
        for (Poste position : Poste.values()) {
            if (contientMinimumPosteJoueur(position)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Permet de savoir si l'équipe contient le minimum de joueurs pour un poste'
     *
     * @param poste le Poste à vérifier
     * @return vrai si elle possède le minimum
     */
    private boolean contientMinimumPosteJoueur(Poste poste) {
        if (getListeJoueurs().stream().filter(joueur -> joueur.getPoste().equals(poste)).count() < poste.getMinJoueur()) {
            return false;
        }
        return true;
    }

}
