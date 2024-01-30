package net.ent.etrs.geststage.model.entities;

import lombok.*;
import net.ent.etrs.geststage.model.entities.exceptions.StagiaireException;
import net.ent.etrs.geststage.model.entities.references.ConstantesMetier;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "STAGE", uniqueConstraints = @UniqueConstraint(columnNames = {"code"}, name = "uk__stage__code"))
@EqualsAndHashCode(of = {"code"}, callSuper = false)
@ToString(of = {"code", "dateDebut", "dateFin"}, callSuper = true)
@NoArgsConstructor
public class Stage extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le code doit être référencé")
    @NotBlank(message = "le code ne doit pas être vide")
    @Pattern(regexp = "[A-Z]{2}\\d{2}")
    //JPA
    @Column(name = "code", length = 4, nullable = false, unique = true)
    private String code;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la date debut doit être référencé")
    //JPA
    @Column(name = "date_debut", nullable = false, unique = false)
    private LocalDate dateDebut;

    //LBK
    @Getter   @Setter
    //BV
    @NotNull(message = "la date fin doit être référencé")
//    @Future(message = "la date fin doit être dans le futur")
    //JPA
    @Column(name = "date_fin", nullable = false, unique = false)
    private LocalDate dateFin;
    
    //BV
    @Size(min = 1, message = "Stage doit avoir au moins 1 stagiaire")
    //JPA
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk__stagiaire__stage_id"))
    private Set<Stagiaire> stagiaires = new HashSet<>();
    
    public Set<Stagiaire> getStagiaires() {
        return Collections.unmodifiableSet(stagiaires);
    }

    public void ajouterStagiaire(Stagiaire stagiaire) throws StagiaireException {
        if (Objects.isNull(stagiaire)) {
            throw new StagiaireException(ConstantesMetier.STAGIAIRE_NULL);
        }
        this.stagiaires.add(stagiaire);
    }

    public void supprimerStagiaire(Stagiaire stagiaire) throws StagiaireException {
        if (Objects.isNull(stagiaire)) {
            throw new StagiaireException(ConstantesMetier.STAGIAIRE_NULL);
        }
        if (!this.stagiaires.contains(stagiaire)) {
            throw new StagiaireException(ConstantesMetier.STAGIAIRE_NOT_EXIST);
        }
        this.stagiaires.remove(stagiaire);
    }
}