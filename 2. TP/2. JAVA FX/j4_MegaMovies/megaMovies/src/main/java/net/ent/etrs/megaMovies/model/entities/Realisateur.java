package net.ent.etrs.megaMovies.model.entities;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "REALISATEUR", uniqueConstraints = @UniqueConstraint(columnNames = {"nom"}, name = "uk__realisateur__nom"))
@EqualsAndHashCode(of = {"nom"}, callSuper = false)
@ToString(of = {"nom"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Realisateur extends AbstractEntity {
    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(min = 1, max = 50, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 50, nullable = false, unique = true)
    private String nom;
}