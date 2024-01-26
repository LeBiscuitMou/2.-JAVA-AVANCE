package model.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import common.utils.C;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of= {"nom", "prenom", "courriel"})
@NoArgsConstructor(access=AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper=false,of= {"nom","prenom"})

@Entity
@Table(name = "CLIENT")
@NamedQueries(
		@NamedQuery(name = "CLIENT_BY_NOM_PRENOM", query = " SELECT c FROM Client c WHERE c.nom = ?1 AND c.prenom = ?2 ")
		)
public class Client extends AbstractEntity {
	
	@Getter
	@Setter
	@NotEmpty(message = C.MSG_NOM_NON_VIDE)
	@Size(min = 3, message = C.MSG_NOM_TAILLE_MIN)
	String nom;
	
	@Getter
	@Setter
	@NotEmpty(message = C.MSG_PRENOM_NON_VIDE)
	@Size(min = 3, message = C.MSG_PRENOM_TAILLE_MIN)
	String prenom;
	
	@Getter
	@Setter
	@NotEmpty(message = C.MSG_MAIL_NON_VIDE)
	@Email(message = C.MSG_MAIL_NON_VALIDE)
	String courriel;

}
