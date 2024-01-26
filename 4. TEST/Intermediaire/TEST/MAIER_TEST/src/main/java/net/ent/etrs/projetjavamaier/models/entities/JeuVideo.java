package net.ent.etrs.projetjavamaier.models.entities;

import lombok.*;
import net.ent.etrs.projetjavamaier.models.entities.comparator.ConsoleComparator;
import net.ent.etrs.projetjavamaier.models.entities.exceptions.JeuVideoException;
import net.ent.etrs.projetjavamaier.models.entities.references.ConstantesMetier;
import net.ent.etrs.projetjavamaier.models.entities.references.Genre;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.time.LocalDate;
import java.util.*;

//LBK
@EqualsAndHashCode(of = {"nom"}, callSuper = false)
@ToString(of = {"nom", "genre", "dateSortie", "studioDev"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

//JPA
@Entity
@Table(name = "JEUVIDEO", uniqueConstraints = {
        @UniqueConstraint(name = "uc_jeuvideo_nom", columnNames = {"nom"})
})
public class JeuVideo extends AbstractEntity implements Comparable<JeuVideo>{
    /******************************************ATTRIBUTS*******************************************/


    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    //BV
    @NotNull(message = "Le genre ne peut pas être null'")
    //JPA
    @Column(name = "genre", nullable = false, length = 40)

    @Enumerated(EnumType.STRING)
    private Genre genre;


    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    //BV
    @NotNull(message = "le nom est null")
    @NotBlank(message = "le nom est vide")
    @Size(min = 3, max = 80)
    //JPA
    @Column(name = "nom", length = 80, nullable = false, unique = true)

    private String nom;

    //JPA
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "JEUVIDEO_ID", foreignKey = @ForeignKey(name = "plateformes__JEUVIDEO_ID__FK"))
    private Set<Console> plateformes = new TreeSet<>(new ConsoleComparator());


    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    //BV
    @NotNull(message = "le fabriquant est null")
    //JPA
    @OneToOne(fetch = FetchType.LAZY)
    private Fabriquant studioDev;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    //BV
    @NotNull(message = "la date de sortie est null")
    @Past
    //@Futur @PastOrPresent
    //JPA
    @Column(name = "date_sortie", nullable = false)

    private LocalDate dateSortie;


    /******************************************GETTERS*******************************************/
    public Set<Console> getPlateformes() {
        return Collections.unmodifiableSet(plateformes);
    }

    /******************************************FONCTIONS*******************************************/

    public void addPlateformes(Console console) throws JeuVideoException {
        if (Objects.isNull(console)) {
            throw new JeuVideoException(ConstantesMetier.CONSOLE_NULL);
        }
        this.plateformes.add(console);


    }

    @Override
    public int compareTo(JeuVideo o) {
        return o.dateSortie.compareTo(this.dateSortie);
    }
}
    
