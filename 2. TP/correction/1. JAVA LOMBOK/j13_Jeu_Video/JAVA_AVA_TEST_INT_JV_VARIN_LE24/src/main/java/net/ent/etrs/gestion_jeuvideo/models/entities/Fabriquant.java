package net.ent.etrs.gestion_jeuvideo.models.entities;

import lombok.*;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Pays;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "fabriquant", uniqueConstraints = @UniqueConstraint(columnNames = {"nom"}, name = "uk__fabriquant$"))
@EqualsAndHashCode(of = {"nom"}, callSuper = false)
@ToString(of = {"nom", "dateCreation", "pays"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Fabriquant extends AbstractEntity {
    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(min = 5, max = 100, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 100, nullable = false, unique = true)
    private String nom;
    
    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "la date de création doit être référencé")
    @Past(message = "la date de création doit être dans le passé")
    //JPA
    @Column(name = "date_de_creation", nullable = false, unique = false)
    private LocalDate dateCreation;
    
    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "le pays doit être référencé")
    //JPA
    @Column(name = "pays", length = 50, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private Pays pays;
}