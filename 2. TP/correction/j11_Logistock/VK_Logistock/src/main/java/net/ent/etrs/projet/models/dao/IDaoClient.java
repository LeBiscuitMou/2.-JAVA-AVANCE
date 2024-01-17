package net.ent.etrs.projet.models.dao;

import net.ent.etrs.projet.models.dao.exceptions.DaoException;
import net.ent.etrs.projet.models.entities.Client;

import java.util.Optional;

public interface IDaoClient extends BaseDao<Client> {

    Optional<Client> findClientByIdentity(String clientNom, String clientPrenom) throws DaoException;
}
