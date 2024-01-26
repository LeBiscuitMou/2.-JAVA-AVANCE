package net.ent.etrs.projet.models.entities;

import lombok.*;
import net.ent.etrs.projet.models.entities.references.Type;
import net.ent.etrs.projet.models.exceptions.SoigneurException;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "soigneur", uniqueConstraints = @UniqueConstraint(columnNames = {"nom", "prenom", "date_de_naissance"}, name = "uk__soigneur$"))
@EqualsAndHashCode(of = {"nom", "prenom", "dateDeNaissance"}, callSuper = false)
@ToString(of = {"nom", "prenom", "dateDeNaissance", "dateArrivee"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Soigneur extends AbstractEntity {
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
    @NotNull(message = "le prénom doit être référencé")
    @NotBlank(message = "le prénom ne doit pas être vide")
    @Size(min = 1, max = 100, message = "la taille du prénom n'est pas valide")
    //JPA
    @Column(name = "prenom", length = 100, nullable = false, unique = false)
    private String prenom;

    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "la date de naissance doit être référencé")
    @Past(message = "la date de naissance doit être dans le passé")
    //JPA
    @Column(name = "date_de_naissance", nullable = false, unique = false)
    private LocalDate dateDeNaissance;

    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "la date d'arrivée doit être référencé")
    @PastOrPresent(message = "la date d'arrivée ne doit pas être dans le futur")
    //JPA
    @Column(name = "date_arrivee", nullable = false, unique = false)
    private LocalDate dateArrivee;
    


}