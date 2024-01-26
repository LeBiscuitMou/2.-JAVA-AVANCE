package garage.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import garage.model.refs.Nationalite;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")

//Annotations JPA
@Entity
@Table(name="MARQUE")
//@Access(AccessType.PROPERTY)

//Annotations Lombok
@FieldDefaults(level=AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@ToString(of="libelle")


@NamedQueries({
	  @NamedQuery(name="rechercherMarqueParLibelle",
	              query="select m from Marque m where m.libelle = :libelle")
	})
public class Marque extends AbstractEntity{
	//JPA
	@Column(name="LIBELLE")
	//BV
	@Size(min=2, message="La marque doit comporter au moins 2 caractères")
	//LBK
	@Getter
	@Setter
	String libelle;

	//JPA
	@Column(name="NATIONALITE")
	@Enumerated(EnumType.STRING)
	//LBK
	@Getter
	@Setter
	Nationalite nationalite;

	/**
	 * Chemin de l'image (vers le systeme de fichiers)
	 */
	//JPA
	@Column(name="CHEMIN_COMPLET")
	//LBK
	@Getter
	@Setter
	String cheminComplet;



}
