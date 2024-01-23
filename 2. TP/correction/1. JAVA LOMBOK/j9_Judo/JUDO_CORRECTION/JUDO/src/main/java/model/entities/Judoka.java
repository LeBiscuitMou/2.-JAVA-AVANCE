package model.entities;

import lombok.*;
import model.entities.references.Categorie;
import model.entities.references.Grade;
import model.exceptions.GradeMinimumAgeException;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "JUDOKA",
        uniqueConstraints = @UniqueConstraint(name = "JUDOKA___NOM__PRENOM__DATE_NAISSANCE___UK", columnNames = {
                "NOM", "PRENOM", "DATE_NAISSANCE"
        })
)
@NoArgsConstructor
@EqualsAndHashCode(of = {"nom", "prenom", "dateNaissance"}, callSuper = false)
@ToString(of = {"nom", "prenom", "dateNaissance"}, callSuper = true)
public class Judoka extends AbstractEntity {

    @Getter
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "PALMARES", joinColumns = @JoinColumn(name = "JUDOKA_ID", foreignKey = @ForeignKey(name = "PALMARES__JUDOKA_ID__FK")))
    @MapKeyJoinColumn(name = "COMPETITION_ID", foreignKey = @ForeignKey(name = "PALMARES__COMPETITION_ID__FK"))
    @Column(name = "CLASSEMENT")
    private final Map<Competition, Integer> palmares = new HashMap<>();

    @Getter
    @Setter
    @NotBlank
    @Length(max = 50)
    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;

    @Getter
    @Setter
    @NotBlank
    @Length(max = 50)
    @Column(name = "PRENOM", nullable = false, length = 50)
    private String prenom;

    @Getter
    @Setter
    @Past
    @Column(name = "DATE_NAISSANCE", nullable = false)
    private LocalDate dateNaissance;

    @Getter
    @Setter
    @NotNull
    @Positive
    @Column(name = "TAILLE", nullable = false)
    private Integer taille;

    @Getter
    @Setter
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORIE", length = 15)
    private Categorie categorie;

    @Getter
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "GRADE", length = 10)
    private Grade grade;


    //    Constructeur
    public Judoka(final String nom, final String prenom, final LocalDate dateNaissance, final Integer taille, final Grade grade, final int poids, final boolean isFeminine) throws GradeMinimumAgeException {
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setDateNaissance(dateNaissance);
        this.setTaille(taille);
        this.setGrade(grade);
        this.setCategorie(Categorie.get(isFeminine, poids));
    }

    public void setGrade(Grade pGrade) throws GradeMinimumAgeException {
        final long age = ChronoUnit.YEARS.between(this.getDateNaissance(), LocalDate.now());
        if (pGrade.getAgeMinimum() > age) {
            throw new GradeMinimumAgeException("Impossible d'attribuer ce grade, il nécessite d'avoir " + age + " ans.");
        }
        this.grade = pGrade;
    }

    /**
     * Méthode pour passer au grade supérieur.
     * Actualise l'attribut grade en y mettant le grade supérieur
     *
     * @throws GradeMinimumAgeException
     */
    public void promouvoir() throws GradeMinimumAgeException {
        final Grade nextGrade = this.getGrade().next();
        if (nextGrade != null) {
            this.setGrade(nextGrade);
        }
    }

    /**
     * Méthode permettant de changer de catégorie.
     * Quand le poids change permet d'actualiser la catégorie du judoka
     *
     * @param pPoids
     */
    public void changeCategorie(final int pPoids) {
        Categorie.get(this.categorie.isFeminin(), pPoids);
    }
}
