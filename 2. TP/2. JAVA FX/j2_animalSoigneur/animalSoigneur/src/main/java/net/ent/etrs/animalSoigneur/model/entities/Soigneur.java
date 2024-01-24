package net.ent.etrs.animalSoigneur.model.entities;

import lombok.*;
import net.ent.etrs.animalSoigneur.model.entities.exceptions.SoigneurException;
import net.ent.etrs.animalSoigneur.model.entities.references.Type;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "SOIGNEUR")
@EqualsAndHashCode(of = {"nom", "prenom", "dateNaissance"}, callSuper = false)
@ToString(of = {"nom", "prenom", "dateNaissance", "dateArrivee"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Soigneur extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(min = 1, max = 50, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 50, nullable = false, unique = false)
    private String nom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le prenom doit être référencé")
    @NotBlank(message = "le prenom ne doit pas être vide")
    @Size(min = 1, max = 50, message = "la taille du prenom n'est pas valide")
    //JPA
    @Column(name = "prenom", length = 50, nullable = false, unique = false)
    private String prenom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la date naissance doit être référencé")
    @Past(message = "la date naissance doit être dans le passé")
    //JPA
    @Column(name = "date_naissance", nullable = false, unique = false)
    private LocalDate dateNaissance;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la date arrivee doit être référencé")
    @PastOrPresent(message = "la date arrivee ne doit pas être dans le futur")
    @Past(message = "la date arrivee doit être dans le passé")
    @Future(message = "la date arrivee doit être dans le futur")
    @FutureOrPresent(message = "la date arrivee ne doit pas être dans le passé")
    //JPA
    @Column(name = "date_arrivee", nullable = false, unique = false)
    private LocalDate dateArrivee;

    //BV
    @Size(min = 1, message = "Soigneur doit avoir au moins 1 Type")
    //JPA
    @ElementCollection
    @JoinColumn(name = "soigneur_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk__type__soigneur_id"))
    private Set<Type> lesTypes = new HashSet<>();

    //BV
    @Size(min = 1, message = "Soigneur doit avoir au moins 1 Animal")
    //JPA
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "soigneur_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk__animal__soigneur_id"))
    private List<Animal> lesAnimals = new ArrayList<>();

    public Set<Type> getLesTypes() {
        return Collections.unmodifiableSet(lesTypes);
    }

    public List<Animal> getLesAnimals() {
        return Collections.unmodifiableList(lesAnimals);
    }

    public void ajouterAnimal(Animal addAnimal) throws SoigneurException {
        if(null == addAnimal){
            throw new SoigneurException(Animal.class.getSimpleName() + " doit être référencé");
        }
        if(this.lesAnimals.contains(addAnimal)){
            throw new SoigneurException(Animal.class.getSimpleName() + " : existe déjà dans la liste des Animal du Soigneur");
        }
        this.lesAnimals.add(addAnimal);
    }

    public void supprimerAnimal(Animal delAnimal) throws SoigneurException {
        if(null == delAnimal){
            throw new SoigneurException(Animal.class.getSimpleName() + " doit être référencé");
        }
        if(!this.lesAnimals.contains(delAnimal)){
            throw new SoigneurException(Animal.class.getSimpleName() + " : n'existe pas dans la liste des Animal du Soigneur");
        }
        this.lesAnimals.remove(delAnimal);
    }

    public void ajouterType(Type addType) throws SoigneurException {
        if(null == addType){
            throw new SoigneurException(Type.class.getSimpleName() + " doit être référencé");
        }
        this.lesTypes.add(addType);
    }

    public void supprimerType(Type delType) throws SoigneurException {
        if(null == delType){
            throw new SoigneurException(Type.class.getSimpleName() + " doit être référencé");
        }
        if(!this.lesTypes.contains(delType)){
            throw new SoigneurException(Type.class.getSimpleName() + " : n'existe pas dans la liste des Type du Soigneur");
        }
        this.lesTypes.remove(delType);
    }
}