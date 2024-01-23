package net.ent.etrs.judo.models.entities;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.ent.etrs.judo.models.entities.references.Categorie;
import net.ent.etrs.judo.models.entities.references.Grade;
import net.ent.etrs.judo.models.exceptions.GradeMinimumAgeException;
import net.ent.etrs.judo.models.exceptions.JudokaException;


import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "judoka", uniqueConstraints = @UniqueConstraint(columnNames = {"nom", "prenom", "dateDeNaissance"}, name = "uk__judoka"))
@EqualsAndHashCode(of = {"nom", "prenom", "dateDeNaissance"}, callSuper = false)
@ToString(of = {"nom", "prenom", "dateDeNaissance", "taille", "categorie", "grade"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Judoka extends AbstractEntity {
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

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le prénom doit être référencé")
    @NotBlank(message = "le prénom ne doit pas être vide")
    @Size(min = 1, max = 50, message = "la taille du prénom n'est pas valide")
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
    @Column(name = "dateDeNaissance", nullable = false, unique = false)
    private LocalDate dateDeNaissance;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la taille doit être référencé")
    @Positive(message = "la taille doit être positif")
    //JPA
    @Column(name = "taille", nullable = false, unique = false)
    private Integer taille;
    
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la catégorie doit être référencé")
    //JPA
    @Column(name = "categorie", length = 15, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private Categorie categorie;
    
    //LBK
    @Getter
    //BV
    @NotNull(message = "le grade doit être référencé")
    //JPA
    @Column(name = "grade", length = 10, nullable = false, unique = false)
    @Enumerated(value = EnumType.STRING)
    private Grade grade;

    @ElementCollection()
    @CollectionTable(name="judoka_competition",
          joinColumns=@JoinColumn(name = "judoka_id", foreignKey = @ForeignKey(name = "fk__judoka_competition__judoka_id")))
    @MapKeyJoinColumn(name="competition_id", nullable = false, foreignKey =  @ForeignKey(name = "fk__judoka_competition__competition_id"))
    @Column(name="classement", nullable = false, unique = false)
    private final Map<Competition, Integer> palmares = new HashMap<>();

    public Judoka(String nom, String prenom, LocalDate dateDeNaissance, Integer taille, Categorie categorie, Grade grade) throws GradeMinimumAgeException {
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setDateDeNaissance(dateDeNaissance);
        this.setTaille(taille);
        this.setCategorie(categorie);
        this.setGrade(grade);
    }

    public void setGrade(Grade grade) throws GradeMinimumAgeException {
        final long age = ChronoUnit.YEARS.between(this.getDateDeNaissance(), LocalDate.now());
        if(grade.getAgeMinimum() > age){
            throw new GradeMinimumAgeException(String.format("%s %s | age actuel : %d ans | age minimum requis pour le grade %s : %d ans",
                    this.nom, this.prenom,
                    age, grade.getNom(), grade.getAgeMinimum()));
        }
        this.grade = grade;
    }
    
    public void ajouterCompetition(Competition addCompetition, Integer classement) throws JudokaException {
        if(null == addCompetition){
            throw new JudokaException(Competition.class.getSimpleName() + " doit être référencé");
        }
        if(null == classement){
            throw new JudokaException("le classement doit être référencé");
        }
        this.palmares.put(addCompetition, classement + this.palmares.get(addCompetition));
    }
    
    public void supprimerCompetition(Competition delCompetition) throws JudokaException {
        if(null == delCompetition){
            throw new JudokaException(Competition.class.getSimpleName() + " doit être référencé");
        }
        if(!this.palmares.containsKey(delCompetition)){
            throw new JudokaException(Competition.class.getSimpleName() + " : n'existe pas dans la liste palmares du Judoka");
        }
        this.palmares.remove(delCompetition);
    }

    public void changeCategorie(int nouveauPoids){
        this.categorie = Categorie.get(this.categorie.isFeminin(), nouveauPoids);
    }

    public void promouvoir() throws GradeMinimumAgeException {
        Grade nextGrade = this.grade.next();
        final long age = ChronoUnit.YEARS.between(this.getDateDeNaissance(), LocalDate.now());
        if(nextGrade != null){
            if(nextGrade.getAgeMinimum() > age){
                throw new GradeMinimumAgeException(
                        String.format("%s %s | age actuel : %d ans | age minimum requis pour le grade %s : %d ans",
                                this.nom, this.prenom,
                                age, nextGrade.getNom(), nextGrade.getAgeMinimum()));
            }
            this.grade = nextGrade;
        }

    }
    
}
