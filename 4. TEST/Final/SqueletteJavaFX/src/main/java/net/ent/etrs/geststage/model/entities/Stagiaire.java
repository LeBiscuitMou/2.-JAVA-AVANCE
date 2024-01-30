package net.ent.etrs.geststage.model.entities;

import lombok.*;
import net.ent.etrs.geststage.model.entities.references.Matiere;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "STAGIAIRE")
@EqualsAndHashCode(of = {"nom", "prenom"}, callSuper = false)
@ToString(of = {"nom", "prenom", "dateNaissance", "moyenne"}, callSuper = true)
@NoArgsConstructor
public class Stagiaire extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(max = 50, message = "la taille du nom n'est pas valide")
//    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "le nom doit être uniquement composé de lettres sans accents, ou de chiffre")
    //JPA
    @Column(name = "nom", length = 50, nullable = false, unique = false)
    private String nom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le prenom doit être référencé")
    @NotBlank(message = "le prenom ne doit pas être vide")
    @Size(max = 50, message = "la taille du prenom n'est pas valide")
//    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "le prenom doit être uniquement composé de lettres sans accents, ou de chiffre")
    //JPA
    @Column(name = "prenom", length = 50, nullable = false, unique = false)
    private String prenom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la date de naissance doit être référencé")
    @Past(message = "la date de naissance doit être dans le passé")
    //JPA
    @Column(name = "date_naissance", nullable = false, unique = false)
    private LocalDate dateNaissance;

    //LBK
    @Getter
    @Setter
    //BV
    @Positive(message = "la moyenne doit être positif")
    @Max(value = 20, message = "la moyenne ne peut pas être au dessus de 20")
    //JPA
    @Column(name = "moyenne", nullable = true, unique = false)
    private Float moyenne;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="stagiaire_notes",
          joinColumns=@JoinColumn(name = "stagiaire_id", foreignKey = @ForeignKey(name = "fk__stagiaire_notes__stagiaire_id")))
    @MapKeyColumn(name="notes", nullable = true)
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name="moyenne", nullable = true, unique = false)
    private Map<Matiere, Float> notes = new HashMap<>();

    public Map<Matiere, Float> getNotes() {
        return Collections.unmodifiableMap(notes);
    }
}