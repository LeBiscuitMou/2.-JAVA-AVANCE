package net.ent.etrs.projetjavamaier.models.daos;

import net.ent.etrs.projetjavamaier.models.daos.base.BaseDao;
import net.ent.etrs.projetjavamaier.models.daos.exception.DaoException;
import net.ent.etrs.projetjavamaier.models.entities.JeuVideo;
import net.ent.etrs.projetjavamaier.models.entities.references.Genre;
import net.ent.etrs.projetjavamaier.models.entities.references.Pays;

import java.util.List;

public interface JeuVideoDao extends BaseDao<JeuVideo> {
    List<JeuVideo> getJeuVideoParGenre(Genre genre) throws DaoException;

    List<JeuVideo> getJeuxVideoDontLeNomCommenceParEtLeFabriquantEstDeTelPays(String debutNom, Pays paysFabriquant) throws DaoException;

}