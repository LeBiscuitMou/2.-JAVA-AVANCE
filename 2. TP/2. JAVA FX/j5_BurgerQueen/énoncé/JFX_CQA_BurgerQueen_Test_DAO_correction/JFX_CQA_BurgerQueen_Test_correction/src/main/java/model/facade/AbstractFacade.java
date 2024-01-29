package model.facade;

import model.dao.DaoClient;
import model.dao.DaoCommande;
import model.dao.DaoFactory;
import model.dao.DaoProduit;

public abstract class AbstractFacade<T> {
	
	DaoClient daoClient = DaoFactory.fabriquerDaoClient();
	DaoCommande daoCommande = DaoFactory.fabriquerDaoCommande();
	DaoProduit daoProduit = DaoFactory.fabriquerDaoProduit();
	
	
}
