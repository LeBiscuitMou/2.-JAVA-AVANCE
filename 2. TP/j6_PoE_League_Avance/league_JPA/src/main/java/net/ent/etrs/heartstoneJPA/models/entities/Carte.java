package net.ent.etrs.heartstoneJPA.models.entities;

import lombok.*;
import net.ent.etrs.heartstoneJPA.models.entities.references.Classe;
import net.ent.etrs.heartstoneJPA.models.entities.references.TypeCarte;
import net.ent.etrs.heartstoneJPA.models.exceptions.CarteException;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
//JPA
@Entity
@Table(name = "carte",uniqueConstraints = @UniqueConstraint(columnNames = {"nom"},name = "uk__carte_nom"))

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
    @Size(max = 50)
    //JPA
    @Column(name ="nom",nullable = false,unique = true,length = 50)
    private String nom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le cout est null")
    @Positive(message = "le cout n'est pas positif")
    @Max(value =10, message="Le cout ne peut pas depasser 10")
    //JPA
@Column(name = "cout",nullable = false,unique = false)
    private Integer cout;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    //BV
    @NotNull(message = "la date de sortie est null")
    //JPA
    @Column(name = "date_sortie",nullable = false,unique = false)
    private LocalDate dateSortie;

    //LBK
    //JPA
    @OneToMany()
    @JoinColumn(name = "carte_id",foreignKey = @ForeignKey(name = "fk__caracteristique_carte_id"))
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
    //JPA
    @Column(name = "classe",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Classe classe;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message ="le type carte est null")
    //JPA
    @Column(name = "type_carte",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TypeCarte typeCarte;

}
