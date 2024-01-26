package garage.model.dao;

import java.util.UUID;

import common.dao.Dao;
import garage.model.entities.Marque;

public interface DaoMarque extends Dao<Marque, UUID>//Dao<Marque,UUID>
{

	
	//Méthodes spécifiques aux marques
	public Marque rechercherMarqueParLibelle(String libelle);
}
