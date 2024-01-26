package garage.model.dao;

import java.util.UUID;

import common.dao.Dao;
import garage.model.dao.jpa.DaoMarqueJpa;
import garage.model.dao.jpa.DaoVoitureJpa;
import garage.model.entities.Marque;


public final class DaoFactory {

	private DaoFactory() {
	}

	// méthode factory	
	
	public static DaoVoiture fabriquerDaoVoitureJpaFactory() {
		return new DaoVoitureJpa();
	}
	
	public static Dao<Marque,UUID> fabriquerDaoMarqueJpaFactory() {
		return new DaoMarqueJpa();
	}

}
