package model.entities;

import common.utils.C;
import lombok.*;
import lombok.experimental.FieldDefaults;
import model.references.TailleProduit;
import model.references.TypeProduit;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@SuppressWarnings("serial")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"nom", "prix"})

@Entity
@Table(name = "PRODUIT", uniqueConstraints = @UniqueConstraint(name = "PRODUIT__NOM__TAILLE_UK", columnNames = {"NOM", "TAILLE_PRODUIT"}))
//@NamedQueries(
//		@NamedQuery(name = "PRODUIT_BY_NOM_TYPE_TAILLE", query = " SELECT c FROM Produit c WHERE c.nom = ?1 AND c.typeProduit =?2 AND c.tailleProduit = ?3 ")
//		)
public class Produit extends AbstractEntity {

    @Getter
    @Setter
    @NotNull(message = C.MSG_NOM_NON_VIDE)
    @NotEmpty(message = C.MSG_NOM_NON_VIDE)
    @Length(min = C.PRODUI_NOM_TAILLE_MIN, max = C.PRODUI_NOM_TAILLE_MAX, message = C.MSG_NOM_TAILLE_MIN)
    @Column(name = "NOM", length = C.PRODUI_NOM_TAILLE_MAX, nullable = false)
    String nom;

    @Getter
    @Setter
    @NotNull(message = C.MSG_PRIX_NULL)
    @DecimalMin(value = "0", inclusive = false, message = C.MSG_PRIX_NON_VALIDE)
    @Column(name = "PRIX")
    BigDecimal prix;

    @Getter
    @Setter
    @NotNull(message = C.MSG_TYPE_PRODUIT_NULL)
    @Column(name = "TYPE_PRODUIT")
    TypeProduit typeProduit;

    @Getter
    @Setter
    @NotNull(message = C.MSG_TAILLE_PRODUIT_NULL)
    @Column(name = "TAILLE_PRODUIT")
    TailleProduit tailleProduit;

}
