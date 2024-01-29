package net.ent.etrs.squelette.model.dao;

import net.ent.etrs.squelette.model.dao.base.BaseDao;
import net.ent.etrs.squelette.model.dao.exceptions.DaoException;
import net.ent.etrs.squelette.model.entities.Client;

public interface IDaoClient extends BaseDao<Client> {

    Client searchClientByNomPrenom(String nom, String prenom) throws DaoException;
}