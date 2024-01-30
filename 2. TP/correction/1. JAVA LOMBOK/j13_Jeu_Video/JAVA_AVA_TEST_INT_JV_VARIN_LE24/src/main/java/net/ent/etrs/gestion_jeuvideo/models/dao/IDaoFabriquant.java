package net.ent.etrs.gestion_jeuvideo.models.dao;

import net.ent.etrs.gestion_jeuvideo.models.dao.exceptions.DaoException;
import net.ent.etrs.gestion_jeuvideo.models.entities.Fabriquant;

import java.util.Optional;

public interface IDaoFabriquant extends BaseDao<Fabriquant> {

    Optional<Fabriquant> findFabriquantByIdentity(String nomFabriquant) throws DaoException;
}