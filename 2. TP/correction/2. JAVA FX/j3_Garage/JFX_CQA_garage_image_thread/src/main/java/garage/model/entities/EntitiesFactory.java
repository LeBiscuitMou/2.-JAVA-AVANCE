package garage.model.entities;

import java.time.LocalDate;

import garage.model.refs.Nationalite;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntitiesFactory {

	public static Marque fabriquerMarque(String libelle, Nationalite nationalite, String cheminComplet) {
		return new Marque(libelle, nationalite, cheminComplet);
	}
	
	public static Voiture fabriquerVoiture(String immatriculation, String modele, Integer puissance, LocalDate miseEnCirculation, Marque marque) {
		return new Voiture(immatriculation, modele, puissance, miseEnCirculation, marque);
	}

}
