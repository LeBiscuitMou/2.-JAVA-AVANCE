package model.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import common.utils.C;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import model.references.TailleProduit;
import model.references.TypeProduit;

@SuppressWarnings("serial")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@ToString(of= {"nom","prix"})

@Entity
@Table(name = "PRODUIT")
@NamedQueries(
		@NamedQuery(name = "PRODUIT_BY_NOM_TYPE_TAILLE", query = " SELECT c FROM Produit c WHERE c.nom = ?1 AND c.typeProduit =?2 AND c.tailleProduit = ?3 ")
		)
public class Produit extends AbstractEntity {
	
	@Getter
	@Setter
	@NotNull(message = C.MSG_NOM_NON_VIDE)
	@NotEmpty(message = C.MSG_NOM_NON_VIDE)
	@Size(min = 3, message = C.MSG_NOM_TAILLE_MIN)
	String nom;

	@Getter
	@Setter
	@DecimalMin(value = "0")
	BigDecimal prix;
	
	@Getter
	@Setter
	@NotNull(message = C.MSG_TYPE_PRODUIT_NULL)
	TypeProduit typeProduit;
	
	@Getter
	@Setter
	@NotNull(message = C.MSG_TAILLE_PRODUIT_NULL)
	TailleProduit tailleProduit;
	
}
