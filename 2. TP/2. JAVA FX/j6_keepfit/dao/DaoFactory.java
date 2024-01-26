package keepfit.model.dao;

import keepfit.model.dao.jpa.DaoAbonneJpa;
import keepfit.model.dao.jpa.DaoPrestationJpa;

public final class DaoFactory {

	private DaoFactory() {
	}

	// méthode factory	
	
	public static DaoAbonneJpa fabriquerDaoAbonneJpa() {
		return new DaoAbonneJpa();
	}		
	
	public static DaoPrestationJpa fabriquerDaoPrestationJpa() {
		return new DaoPrestationJpa();
	}		
}
