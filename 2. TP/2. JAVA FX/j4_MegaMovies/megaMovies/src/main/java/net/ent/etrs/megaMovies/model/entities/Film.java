package net.ent.etrs.megaMovies.model.entities;

import lombok.*;
import net.ent.etrs.megaMovies.model.entities.references.Genre;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "FILM", uniqueConstraints = @UniqueConstraint(columnNames = {"titre"}, name = "uk__film__titre"))
@EqualsAndHashCode(of = {"titre"}, callSuper = false)
@ToString(of = {"titre", "dateSortie", "genre"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Film extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le titre doit être référencé")
    @NotBlank(message = "le titre ne doit pas être vide")
    @Size(min = 1, max = 50, message = "la taille du titre n'est pas valide")
    //JPA
    @Column(name = "titre", length = 50, nullable = false, unique = true)
    private String titre;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la date sortie doit être référencé")
    @PastOrPresent(message = "la date sortie ne doit pas être dans le futur")
    //JPA
    @Column(name = "date_sortie", nullable = false, unique = false)
    private LocalDate dateSortie;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le genre doit être référencé")
    //JPA
    @Column(name = "genre", length = 50, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le realisateur doit être référencé")
    //JPA
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "realisateur_id", referencedColumnName = "nom", foreignKey = @ForeignKey(name = "fk__film_realisateur__realisateur_id"))
    private Realisateur realisateur;
}