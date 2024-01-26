package garage.model.entities;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")

@Entity
@Table(name="VOITURE")
//@Access(AccessType.PROPERTY)

@NamedQueries({
	  @NamedQuery(name="listerVoituresParPuissance",
	              query="select v from Voiture v order by v.puissance desc"),
	  @NamedQuery(name="rechercherVoitureParImmatriculation",
	              query="select v from Voiture v where v.immatriculation= :immat"),
	  @NamedQuery(name="compterVoituresDuneMarque",
	  query="select count(v.immatriculation) from Voiture v where v.marque= :m"),
	  @NamedQuery(name="rechercherVoitureParModele",
	  query=" select v from Voiture v where v.modele like '%:mod%' "),
	  @NamedQuery(name="trouverVoituresParMarque",
	  query=" select v from Voiture v where v.marque= :m")
	  
//	  
	})

//Annotations Lombok
@FieldDefaults(level=AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Voiture extends AbstractEntity{
	//Atributs	
	
	@Column(name="IMMAT")
	@Pattern(regexp="[A-Z]{2}-\\d{3}-[A-Z]{2}",message="L'immatriculation doit respecter le format AA-123-BB")
	String immatriculation;

	@Column(name="MODELE")
	@Size(min=2, message="Le modèle doit comporter au moins 2 caractères")
	String modele;	

	@Column(name="PUISSANCE")
	@Min(value=0,message="La puissance ne peut etre négative")
	Integer puissance;			

	@Column(name="mec")	
	LocalDate miseEnCirculation;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="MARQUE_ID")
	@Valid	//Marque marque;	
	Marque marque;

	@Override
	public String toString() {
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
		  
		StringBuilder sb = new StringBuilder();
		sb.append( "Immatriculation:" + immatriculation + "\n");
		//sb.append( "Marque:"+getMarque().getLibelleMarque()+"\n");
		sb.append("Modele:"+ modele + "\n");
		sb.append("Puissance:"+puissance+"\n");
		if (this.miseEnCirculation!=null)			
			sb.append("Date de mise en circulation:" + formatter.format(this.getMiseEnCirculation())) ;
		else
			sb.append("Date de mise en circulation:N/A");
		return sb.toString();
	}
}

