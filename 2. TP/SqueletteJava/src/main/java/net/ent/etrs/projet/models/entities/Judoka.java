package net.ent.etrs.projet.models.entities;

import lombok.*;
import net.ent.etrs.projet.models.exceptions.JudokaException;
import net.ent.etrs.projet.models.references.Categorie;
import net.ent.etrs.projet.models.references.Grade;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "judoka", uniqueConstraints = @UniqueConstraint(columnNames = {"nom", "prenom"}, name = "uk__judoka__nom_prenom"))
@EqualsAndHashCode(of = {"nom", "prenom", "dateDeNaissance"}, callSuper = false)
@ToString(of = {"nom", "prenom", "dateDeNaissance", "categorie", "grade", "taille"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Judoka extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la categorie doit être référencé")
    //JPA
    @Column(name = "categorie", length = 15, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private Categorie categorie;

    //LBK
    @Getter
    @Setter
    @NonNull
    //BV
    @NotNull(message = "la date de naissance doit être référencé")
    @Past(message = "la date de naissance doit être dans le passé")
    //JPA
    @Column(name = "dateDeNaissance", nullable = false)
    private LocalDate dateDeNaissance;

    //LBK
    @Getter
    @Setter
    @NonNull
    //BV
    @NotNull(message = "le grade doit être référencé")
    //JPA
    @Column(name = "grade", length = 10, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private Grade grade;

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
    
    @ElementCollection()
    @CollectionTable(name="judoka_competition",
          joinColumns=@JoinColumn(name = "judoka_id", foreignKey = @ForeignKey(name = "fk__judoka_competition__judoka_id")))
    @MapKeyJoinColumn(name="competition_id", nullable = false, foreignKey =  @ForeignKey(name = "fk__judoka_competition__competition_id"))
    @Column(name="classement", nullable = false, unique = false)
    private final Map<Competition, Integer> palmares = new HashMap<>();

    public Map<Competition, Integer> getPalmares() {
        return Collections.unmodifiableMap(palmares);
    }
    
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
    @Positive(message = "la taille doit être positif")
    //JPA
    @Column(name = "taille", nullable = false, unique = false)
    private Integer taille;
}