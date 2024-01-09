package net.ents.etrs.projethockey.model.entities;

import lombok.*;
import net.ents.etrs.projethockey.model.entities.execptions.EquipeException;
import net.ents.etrs.projethockey.model.entities.references.ConstantesMetier;
import net.ents.etrs.projethockey.model.entities.references.Poste;
import net.ents.etrs.projethockey.model.entities.references.Ville;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "equipe", uniqueConstraints = @UniqueConstraint(name = "UK_equipe_nom_ville", columnNames="nom_ville"))
@EqualsAndHashCode(of = {"nom","ville"}, callSuper = false)
@ToString(of = {"nom","ville"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)


public class Equipe extends AbstractEntity {

    @Getter
    @Setter
    @NotBlank
    @Length(min = 3, max = 100,message = "le nom de l'equipe doit être entre 3 et 100 caractères")
    @Column(name = "nom", nullable = false, length = 100,unique = true)

    private String nom;


    @Getter
    @Setter
    @NotBlank
    @Length(min = 1, max = 50,message = "le nom de la ville doit être entre 1 et 50 caractères")
    @Column(name = "ville", nullable = false, length = 50,unique = true)
    private Ville ville;

@OneToMany(fetch = FetchType.LAZY)
@JoinColumn(name = "equipe_id", foreignKey = @ForeignKey(name = "joueur__equipe_id__FK"))

    private Set<Joueur> listeJoueurs= new HashSet<>();


    public Set<Joueur> getListeJoueurs() {
        return Collections.unmodifiableSet(listeJoueurs);
    }

    /* ******************************** FONCTIONS ******************************** */
    private boolean contientMinimumPosteJoueur(Poste poste) throws EquipeException {
     if(Objects.isNull(poste)) {
         throw new EquipeException(ConstantesMetier.ERR_JOUEUR_POSTE_INVALIDE);
     }

     int compteurJoueur = 0 ;

        for(Joueur joueur : this.getListeJoueurs()) {
            if(joueur.getPoste().equals(poste)) {
                compteurJoueur++;
            }
        }

        switch (poste) {
            case GARDIEN :
                if(compteurJoueur < Poste.GARDIEN.getMinJoueur()) {
                    throw new EquipeException(ConstantesMetier.ERR_NB_JOUEUR_MIN_GARDIEN);
                }
                break;
                case DEFENSEUR :
                    if(compteurJoueur < Poste.DEFENSEUR.getMinJoueur()) {
                        throw new EquipeException(ConstantesMetier.ERR_NB_JOUEUR_MIN_DEFENSEUR);
                    }
                    break;
                case ATTAQUANT :
                    if(compteurJoueur < Poste.ATTAQUANT.getMinJoueur()) {
                        throw new EquipeException(ConstantesMetier.ERR_NB_JOUEUR_MIN_ATTAQUANT);
                    }
                    break;
                default:
                    throw new EquipeException(ConstantesMetier.ERR_JOUEUR_POSTE_INVALIDE);
        }



return true;
    }

    public boolean estValide() throws EquipeException {
        return contientMinimumJoueurPoste();
    }

    private boolean contientMinimumJoueurPoste() throws EquipeException {
        int nbGardien = 0;
        int nbAttaquant = 0;
        int nbDefenseur = 0;
        for (Joueur joueur : this.getListeJoueurs()) {
            switch (joueur.getPoste()) {
                case ATTAQUANT:
                    nbAttaquant++;
                    break;
                case DEFENSEUR:
                    nbDefenseur++;
                    break;
                case GARDIEN:
                    nbGardien++;
                    break;
                default:
                    throw new EquipeException(ConstantesMetier.ERR_JOUEUR_POSTE_INVALIDE);
            }

            if (nbAttaquant < Poste.ATTAQUANT.getMinJoueur()) {

                throw new EquipeException(ConstantesMetier.ERR_NB_JOUEUR_MIN_ATTAQUANT);
            }
            if (nbDefenseur < Poste.DEFENSEUR.getMinJoueur()) {
                throw new EquipeException(ConstantesMetier.ERR_NB_JOUEUR_MIN_DEFENSEUR);
            }
            if (nbGardien < Poste.GARDIEN.getMinJoueur()) {
                throw new EquipeException(ConstantesMetier.ERR_NB_JOUEUR_MIN_GARDIEN);

            }


        }return true;
    }
    public void ajouterJoueur(Joueur joueur) throws EquipeException {

        if(Objects.isNull(joueur)) {
            throw new EquipeException(ConstantesMetier.ERR_JOUEUR_NULL);
        }
        this.listeJoueurs.add(joueur);

    }

    public void supprimerJoueur(Joueur joueur) throws EquipeException {
        if(Objects.isNull(joueur)) {
            throw new EquipeException(ConstantesMetier.ERR_JOUEUR_NULL);
        }
        this.listeJoueurs.remove(joueur);
    }

    public int getTotalPoints(){
        return listeJoueurs.stream().mapToInt(Joueur::getNbPoint).sum();
    }

}
