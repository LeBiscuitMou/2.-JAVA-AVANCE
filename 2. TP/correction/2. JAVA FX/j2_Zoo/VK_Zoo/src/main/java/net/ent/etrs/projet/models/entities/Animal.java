package net.ent.etrs.projet.models.entities;

import lombok.*;
import net.ent.etrs.projet.models.entities.references.Type;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "animal", uniqueConstraints = @UniqueConstraint(columnNames = {"nom", "date_de_naissance"}, name = "uk__animal$"))
@EqualsAndHashCode(of = {"nom", "dateDeNaissance"}, callSuper = false)
@ToString(of = {"nom", "dateDeNaissance", "poids", "taille", "dangereux", "type"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Animal extends AbstractEntity {
    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(min = 1, max = 100, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 100, nullable = false, unique = false)
    private String nom;

    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "la date de naissance doit être référencé")
    @PastOrPresent(message = "la date de naissance ne doit pas être dans le futur")
    //JPA
    @Column(name = "date_de_naissance", nullable = false, unique = false)
    private LocalDate dateDeNaissance;

    //LBK
    @Getter   @Setter
    //BV
    @Positive(message = "le poids doit être positif")
    //JPA
    @Column(name = "poids", nullable = false, unique = false)
    private double poids;

    //LBK
    @Getter   @Setter
    //BV
    @Positive(message = "la taille doit être positif")
    //JPA
    @Column(name = "taille", nullable = false, unique = false)
    private double taille;

    //LBK
    @Getter   @Setter
    //JPA
    @Column(name = "dangereux", nullable = false, unique = false)
    private boolean dangereux;
    
    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "le type doit être référencé")
    //JPA
    @Column(name = "type", length = 50, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private Type type;


}