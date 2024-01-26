package garage.model.dao;

import java.util.List;
import java.util.UUID;

import common.dao.Dao;
import garage.model.entities.Marque;
import garage.model.entities.Voiture;

public interface DaoVoiture extends Dao<Voiture,UUID> {

	//Méthodes spécifiques aux voitures
	
	public List<Voiture> listerParPuissance();

	public Voiture rechercherParImmat(String pimmat);

	public List<Voiture> rechercherParModele(String mod);

	public List<Voiture> trouverVoituresParMarque(Marque m);

	public long compterVoituresParMarque(Marque m);

}
