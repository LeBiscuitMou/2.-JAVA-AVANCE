package net.ent.etrs.projetjavamaier.models.entities;

import lombok.*;
import net.ent.etrs.projetjavamaier.models.entities.references.Pays;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

//LBK
//LBK
@EqualsAndHashCode(of = {"nom"}, callSuper = false)
@ToString(of = {"nom", "dateCreation", "pays"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

//JPA
@Entity
@Table(name = "FABRIQUANT", uniqueConstraints = {
        @UniqueConstraint(name = "uc_fabriquant_nom", columnNames = {"nom"})
})
public class Fabriquant extends AbstractEntity {
    /******************************************ATTRIBUTS*******************************************/
    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    //BV
    @NotNull(message = "le nom est null")
    @NotBlank(message = "le nom est vide")
    @Size(min = 5, max = 100)
    //JPA
    @Column(name = "nom", length = 100, nullable = false, unique = true)
    private String nom;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    //BV
    @NotNull(message = "la date de creation est null")
    @Past
    //@Futur @PastOrPresent
    //JPA
    @Column(name = "date_creation", nullable = false)
    private LocalDate dateCreation;


    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    //BV
    @NotNull(message = "Le pays ne peut pas être null'")
    //JPA
    @Column(name = "pays", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)

    private Pays pays;


    /******************************************GETTERS*******************************************/


    /******************************************FONCTIONS*******************************************/


}
    
