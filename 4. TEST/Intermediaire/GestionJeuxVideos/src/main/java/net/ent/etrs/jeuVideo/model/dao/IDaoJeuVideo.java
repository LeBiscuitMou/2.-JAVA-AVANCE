package net.ent.etrs.jeuVideo.model.dao;

import net.ent.etrs.jeuVideo.model.dao.base.BaseDao;
import net.ent.etrs.jeuVideo.model.dao.exceptions.DaoException;
import net.ent.etrs.jeuVideo.model.entities.JeuVideo;
import net.ent.etrs.jeuVideo.model.entities.references.Genre;
import net.ent.etrs.jeuVideo.model.entities.references.Pays;

import java.util.List;

public interface IDaoJeuVideo extends BaseDao<JeuVideo> {

    List<JeuVideo> findAllGamesByGenre(Genre genre) throws DaoException;

    List<JeuVideo> recupererJeuxVideoDontLeNomCommenceParEtLeFabriquantEstDeTelPays(String debutNom, Pays paysFabriquant);
}