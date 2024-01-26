package model.dao;

import model.dao.base.BaseDao;
import model.entities.Client;
import model.entities.Commande;

import java.io.Serializable;
import java.util.List;

public interface DaoCommande extends BaseDao<Commande, Serializable> {

//	public void initialisation(ClientFacade facadeClient, ProduitFacade facadeProduit) throws BusinessException;

    List<Commande> searchCommandeByClient(Client client);

}
