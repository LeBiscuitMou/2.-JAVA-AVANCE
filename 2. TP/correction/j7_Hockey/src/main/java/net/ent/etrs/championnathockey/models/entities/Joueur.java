package net.ent.etrs.championnathockey.models.entities;

import lombok.*;
import net.ent.etrs.championnathockey.models.entities.references.ConstanteMetier;
import net.ent.etrs.championnathockey.models.entities.references.Poste;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Entity
@Table(name = "JOUEUR", uniqueConstraints = @UniqueConstraint(name = "JOUEUR___NOM__PRENOM__DATE_NAISSANCE___UK", columnNames = {"NOM", "PRENOM", "DATE_NAISSANCE"}))

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"nom", "prenom", "dateNaissance"})
@ToString(callSuper = true, of = {"nom", "prenom", "dateNaissance", "poste", "nbPoint"})
public class Joueur extends AbstractEntity {

    //LBK
    @Getter
    @Setter
    //BV
    @NotBlank(message = ConstanteMetier.JOUEUR_METIER_NOM_VIDE)
    @NotNull(message = ConstanteMetier.JOUEUR_METIER_NOM_NULL)
    @Length(min = 2, max = 50, message = ConstanteMetier.JOUEUR_METIER_NOM_LENGTH)
    //JPA
    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotBlank(message = ConstanteMetier.JOUEUR_METIER_PRENOM_VIDE)
    @NotNull(message = ConstanteMetier.JOUEUR_METIER_PRENOM_NULL)
    @Length(min = 2, max = 30, message = ConstanteMetier.JOUEUR_METIER_PRENOM_LENGTH)
    //JPA
    @Column(name = "PRENOM", nullable = false, length = 30)
    private String prenom;

    //LBK
    @Getter
    @Setter
    //BV
    @PastOrPresent(message = ConstanteMetier.JOUEUR_METIER_DATE_NAISSANCE_PASSE)
    @NotNull(message = ConstanteMetier.JOUEUR_METIER_DATE_NAISSANCE_NULL)
    //JPA
    @Column(name = "DATE_NAISSANCE", nullable = false)
    private LocalDate dateNaissance;

    //LBK
    @Getter
    @Setter
    //BV
    @PositiveOrZero(message = ConstanteMetier.JOUEUR_METIER_NBPOINT_POSITIF)
    @NotNull(message = ConstanteMetier.JOUEUR_METIER_NBPOINT_NULL)
    //JPA
    @Column(name = "NB_POINT", nullable = false)
    private Integer nbPoint = 0;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = ConstanteMetier.EQUIPE_METIER_POSITION_NULL)
    //JPA
    @Enumerated(EnumType.STRING)
    @Column(name = "POSTE", nullable = false, length = 30)
    private Poste poste;

}
