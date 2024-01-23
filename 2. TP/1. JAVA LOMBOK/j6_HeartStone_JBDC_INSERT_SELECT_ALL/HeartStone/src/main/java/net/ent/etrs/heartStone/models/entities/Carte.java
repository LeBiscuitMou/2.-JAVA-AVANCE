package net.ent.etrs.heartStone.models.entities;

import lombok.*;
import net.ent.etrs.heartStone.models.exceptions.CarteException;
import net.ent.etrs.heartStone.models.references.Classe;
import net.ent.etrs.heartStone.models.references.TypeCarte;

import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(of = "nom", callSuper = false)
@ToString(of = {"nom", "cout", "dateSortie", "classe", "typeCarte"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Carte extends AbstractEntity {

    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    //BV
    @NotNull(message = "le nom est null")
    @NotBlank(message = "le nom est vide")
    private String nom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le cout est null")
    @Positive(message = "le cout n'est pas positif")
    @Max(value =10, message="Le cout ne peut pas depasser 10")
    private Integer cout;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    //BV
    @NotNull(message = "la date de sortie est null")
    private LocalDate dateSortie;

    //LBK
    @OneToMany
    @JoinColumn(name = "carte_id", foreignKey = @ForeignKey(name = "fk__carcteristique_carte_id"))
    private Set<Caracteristique> caracteristiques = new HashSet<>();

    public Set<Caracteristique> getCaracteristiques() {
        return Collections.unmodifiableSet(this.caracteristiques);
    }

    public void ajouterCaracteristique(Caracteristique caracteristique) throws CarteException {
        if (null == caracteristique) {
            throw new CarteException("Caracteristique null");
        }
        if (caracteristiques.contains(caracteristique)) {
            throw new CarteException("Caracteristique deja existante");
        }
        this.caracteristiques.add(caracteristique);
    }

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la classe est null")
    private Classe classe;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message ="le type carte est null")
    private TypeCarte typeCarte;

}
