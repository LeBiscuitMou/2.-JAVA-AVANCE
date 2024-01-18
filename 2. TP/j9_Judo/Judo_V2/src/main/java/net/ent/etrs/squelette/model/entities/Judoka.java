package net.ent.etrs.squelette.model.entities;

import lombok.*;
import net.ent.etrs.squelette.model.entities.references.Categorie;
import net.ent.etrs.squelette.model.entities.references.Grade;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "judoka")
@EqualsAndHashCode(of = {"nom", "prenom", "dateNaissance"}, callSuper = false)
@ToString(of = {"nom", "prenom", "dateNaissance", "categorie", "grade", "taille"}, callSuper = true)
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Judoka extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    @NonNull
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(max = 50, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 50, nullable = false, unique = false)
    private String nom;
    
    //LBK
    @Getter
    @Setter
    @NonNull
    //BV
    @NotNull(message = "le prenom doit être référencé")
    @NotBlank(message = "le prenom ne doit pas être vide")
    @Size(max = 50, message = "la taille du prenom n'est pas valide")
    //JPA
    @Column(name = "prenom", length = 50, nullable = false, unique = false)
    private String prenom;
    
    //LBK
    @Getter
    @Setter
    @NonNull
    //BV
    @NotNull(message = "la date naissance doit être référencé")
    @Past(message = "la date naissance doit être dans le passé")
    //JPA
    @Column(name = "dateNaissance", nullable = false, unique = false)
    private LocalDate dateNaissance;
    
    //LBK
    @Getter
    @Setter
    @NonNull
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
    @NotNull(message = "le grade doit être référencé")
    //JPA
    @Column(name = "grade", length = 10, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private Grade grade;
    
    //LBK
    @Getter
    @Setter
    @NonNull
    //BV
    @Positive(message = "la taille doit être positif")
    //JPA
    @Column(name = "taille", nullable = false, unique = false)
    private Integer taille;
    
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="judoka_competition",
          joinColumns=@JoinColumn(name = "judoka_id", foreignKey = @ForeignKey(name = "fk__judoka_competition__judoka_id")))
    @MapKeyJoinColumn(name="competition_id", nullable = false, foreignKey =  @ForeignKey(name = "fk__judoka_competition__competition_id"))
    @Column(name="classement", nullable = false, unique = false)
    private Map<Competition, Integer> palmares = new HashMap<>();

    public Map<Competition, Integer> getPalmares() {
        return Collections.unmodifiableMap(palmares);
    }
}