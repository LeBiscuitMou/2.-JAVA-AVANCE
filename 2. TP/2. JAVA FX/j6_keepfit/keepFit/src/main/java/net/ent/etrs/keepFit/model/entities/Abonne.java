package net.ent.etrs.keepFit.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import lombok.*;
import net.ent.etrs.keepFit.model.entities.exceptions.AbonneException;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "ABONNE")
@EqualsAndHashCode(of = {"nom", "prenom"}, callSuper = false)
@ToString(of = {"nom", "prenom", "dateInscription"}, callSuper = true)
public class Abonne extends AbstractEntity {
    @Getter
    private static final BigDecimal ABONNEMENT_BASE = BigDecimal.valueOf(240);
    
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(min = 1, max = 255, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 255, nullable = false, unique = false)
    private String nom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le prenom doit être référencé")
    @NotBlank(message = "le prenom ne doit pas être vide")
    @Pattern(regexp = "^[a-zA-Z]+$")
    @Size(min = 1, max = 255, message = "la taille du prenom n'est pas valide")
    //JPA
    @Column(name = "prenom", length = 255, nullable = false, unique = false)
    private String prenom;

    //LBK
    @Getter
    //BV
    @PastOrPresent(message = "la date inscription ne doit pas être dans le futur")
    //JPA
    @Column(name = "date_inscription", nullable = false, unique = false)
    private LocalDate dateInscription;

    //JPA
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "abonne_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk__prestation__abonne_id"))
    private List<Prestation> lstPrestations = new ArrayList<>();

    public Abonne() {
        this.dateInscription = LocalDate.now();
    }

    public Abonne(String nom, String prenom, LocalDate dateInscription) {
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setDateInscription(dateInscription);
    }

    public List<Prestation> getLstPrestations() {
        return Collections.unmodifiableList(lstPrestations);
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = Objects.requireNonNullElseGet(dateInscription, LocalDate::now);
    }

    public void souscrire(Prestation addPrestation) throws AbonneException {
        if(null == addPrestation){
            throw new AbonneException(Prestation.class.getSimpleName() + " doit être référencé");
        }
        if(this.lstPrestations.contains(addPrestation)){
            throw new AbonneException(Prestation.class.getSimpleName() + " : existe déjà dans la liste des Prestation du Abonne");
        }
        this.lstPrestations.add(addPrestation);
    }

    public void resiler(Prestation delPrestation) throws AbonneException {
        if(null == delPrestation){
            throw new AbonneException(Prestation.class.getSimpleName() + " doit être référencé");
        }
        if(!this.lstPrestations.contains(delPrestation)){
            throw new AbonneException(Prestation.class.getSimpleName() + " : n'existe pas dans la liste des Prestation du Abonne");
        }
        this.lstPrestations.remove(delPrestation);
    }

    public BigDecimal calculerAbonnement() {
        BigDecimal result = BigDecimal.ZERO;
        for(Prestation p : lstPrestations){
            result = result.add(p.getCout());
        }
        return result;
    }
}