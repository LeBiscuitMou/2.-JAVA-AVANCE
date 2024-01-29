package model.entities;

import common.utils.C;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@SuppressWarnings("serial")
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of = {"nom", "prenom", "courriel"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"courriel"})

@Entity
@Table(name = "CLIENT", uniqueConstraints = @UniqueConstraint(name = "CLIENT__COURRIEL__UK", columnNames = "COURRIEL"))
//@NamedQueries(
//		@NamedQuery(name = "CLIENT_BY_NOM_PRENOM", query = " SELECT c FROM Client c WHERE c.nom = ?1 AND c.prenom = ?2 ")
//		)
public class Client extends AbstractEntity {

    @Getter
    @Setter
    @NotEmpty(message = C.MSG_NOM_NON_VIDE)
    @Length(min = C.CLIENT_NOM_TAILLE_MIN, max = C.CLIENT_NOM_TAILLE_MAX, message = C.MSG_NOM_TAILLE_MIN)
    @Column(name = "NOM", length = C.CLIENT_NOM_TAILLE_MAX, nullable = false)
    String nom;

    @Getter
    @Setter
    @NotEmpty(message = C.MSG_PRENOM_NON_VIDE)
    @Length(min = C.CLIENT_PRENOM_TAILLE_MIN, max = C.CLIENT_PRENOM_TAILLE_MAX, message = C.MSG_PRENOM_TAILLE_MIN)
    @Column(name = "PRENOM", length = C.CLIENT_PRENOM_TAILLE_MAX, nullable = false)
    String prenom;

    @Getter
    @Setter
    @NotEmpty(message = C.MSG_MAIL_NON_VIDE)
    @Email(message = C.MSG_MAIL_NON_VALIDE)
    String courriel;

//    public void setCourriel(@NotEmpty String courriel) {
//        this.courriel = courriel;
//    }


}
