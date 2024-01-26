package model.dao;

import model.dao.base.BaseDao;
import model.entities.Client;

import java.io.Serializable;

public interface DaoClient extends BaseDao<Client, Serializable> {

//	void initialisationClient();

    Client searchClientByNomPrenom(String nom, String prenom);

}
