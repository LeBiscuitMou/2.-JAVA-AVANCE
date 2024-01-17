package net.ent.etrs.logistock.model.dao;

import net.ent.etrs.logistock.model.dao.base.BaseDao;
import net.ent.etrs.logistock.model.entities.Client;
import net.ent.etrs.logistock.model.exceptions.DaoException;

import java.io.Serializable;
import java.util.Optional;

public interface IDaoClient extends BaseDao<Client, Serializable> {
    Optional<Client> getClientById(Long pId) throws DaoException;
}
