package net.ent.etrs.projetjavamaier.models.entities;

import lombok.*;
import net.ent.etrs.projetjavamaier.models.entities.exceptions.ConsoleException;
import net.ent.etrs.projetjavamaier.models.entities.references.ConstantesMetier;
import net.ent.etrs.projetjavamaier.models.entities.references.Pays;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

//LBK
@EqualsAndHashCode(of = {"nom"}, callSuper = false)
@ToString(of = {"nom", "fabriquant"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

//JPA
@Entity
@Table(name = "CONSOLE", uniqueConstraints = {
        @UniqueConstraint(name = "uc_console_nom", columnNames = {"nom"})
})
public class Console extends AbstractEntity implements Comparable<Console> {
    /******************************************ATTRIBUTS*******************************************/

    //JPA
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "PAYS_CONSOLE", joinColumns = @JoinColumn(name = "PAYS_ID", foreignKey = @ForeignKey(name = "PAYS_CONSOLE___PAYS_ID___FK")))
    @MapKeyJoinColumn(name = "CONSOLE_id", nullable = false, foreignKey = @ForeignKey(name = "PAYS_CONSOLE___CONSOLE_ID___FK"))
    @Column(name = "sorties", nullable = false)
    private Map<Pays, LocalDate> sorties = new HashMap<>();


    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    //BV
    @NotNull(message = "le nom est null")
    @NotBlank(message = "le nom est vide")
    @Size(min = 2, max = 50)
    //JPA
    @Column(name = "nom", length = 50, nullable = false, unique = true)

    private String nom;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    //BV
    @NotNull(message = "le fabriquant est null")
    //JPA
    @OneToOne(fetch = FetchType.LAZY)
    private Fabriquant fabriquant;

    /******************************************GETTERS*******************************************/
    public Map<Pays, LocalDate> getSorties() {
        return Collections.unmodifiableMap(sorties);
    }
/******************************************FONCTIONS*******************************************/

public void setSortiesPays(Pays pays, LocalDate dateSortie) throws ConsoleException {
    if (Objects.isNull(pays)|| Objects.isNull(dateSortie)) {
                throw new ConsoleException(ConstantesMetier.ATTENTION_OBJET_NULL);
            }

    if(sorties.containsKey(pays)) {
        this.sorties.put(pays, dateSortie);
    }

}


    @Override
    public int compareTo(Console o) {
        return this.nom.compareTo(o.nom);
    }
}
    
