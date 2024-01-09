package net.ents.etrs.projethockey.model.entities;

import lombok.*;
import net.ents.etrs.projethockey.model.entities.references.ConstantesMetier;
import net.ents.etrs.projethockey.model.entities.references.Poste;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
@Entity
@Table(name = "joueur", uniqueConstraints = @UniqueConstraint(name = "UK_joueur_nom_prenom_date_naissance", columnNames = "nom_prenom_date_naissance"))
@EqualsAndHashCode(of = {"nom","prenom","dateNaissance"}, callSuper = false)
@ToString(of = {"nom","prenom","dateNaissance","nbPoint","poste"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Joueur extends AbstractEntity {
    @Getter
    @Setter
    @NotBlank
    @Length(min = 3, max = 50,message = "le prenom du joueur doit être entre 3 et 50 caractères")
    @Column(name = "prenom", nullable = false, length = 50,unique = true)

    private String prenom;
    @Getter
    @Setter
    @NotBlank
    @Length(min = 3, max = 30,message = "le nom du joueur doit être entre 3 et 30 caractères")
    @Column(name = "nom", nullable = false, length = 30,unique = true)
    private String nom;


    @Getter
    @Setter
    @Past(message = ConstantesMetier.ERR_JOUEUR_DATE_NAISSANCE_INVALIDE)
    @Column(name = "date_naissance",nullable = false,unique = true)
    private LocalDate dateNaissance;

    @Getter
    @Setter
    @Length(min = 1,message = "le nb de points doit etre superieur à 1")
    @Column(name = "nb_point",nullable = false)
    private Integer nbPoint;

    @Getter
    @Setter
    @Length(min = 1,max = 15,message = "la longueur du poste doit etre inferieur a 15 caractères")
    private Poste poste;
    
    

    
    
}
