package keepfit.model.dao;

import java.util.UUID;

import keepfit.model.entities.Prestation;

public interface DaoPrestation extends Dao<Prestation,UUID>{
	
	//méthodes specifiques aux prestations
	
	/**
	 * Verifie s'il existe déja des prestations en base de données ou non
	 * @return vrai/faux
	 */
	public boolean existeDejaDesPrestations();
}
