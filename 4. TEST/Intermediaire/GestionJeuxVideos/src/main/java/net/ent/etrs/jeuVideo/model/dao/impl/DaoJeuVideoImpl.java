package net.ent.etrs.jeuVideo.model.dao.impl;

import net.ent.etrs.jeuVideo.model.dao.IDaoJeuVideo;
import net.ent.etrs.jeuVideo.model.dao.base.JpaBaseDao;
import net.ent.etrs.jeuVideo.model.dao.exceptions.DaoException;
import net.ent.etrs.jeuVideo.model.entities.JeuVideo;
import net.ent.etrs.jeuVideo.model.entities.references.Genre;
import net.ent.etrs.jeuVideo.model.entities.references.Pays;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class DaoJeuVideoImpl extends JpaBaseDao<JeuVideo> implements IDaoJeuVideo {
    @Override
    public List<JeuVideo> findAllGamesByGenre(Genre genre) throws DaoException {
        try {
            TypedQuery<JeuVideo> query = this.em.createQuery(
                            """
                            SELECT j
                            FROM JeuVideo j
                            WHERE j.genre = :genre
                            """
                    , JeuVideo.class);
            query.setParameter("genre", genre);

            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public List<JeuVideo> recupererJeuxVideoDontLeNomCommenceParEtLeFabriquantEstDeTelPays(String debutNom, Pays paysFabriquant) throws DaoException {
        try {
            TypedQuery<JeuVideo> query = this.em.createQuery(
                        """
                                SELECT j FROM JeuVideo j
                                WHERE j.nom LIKE :debut%
                                AND j.studioDev.pays = :pays
                                """
                    , JeuVideo.class);
            query.setParameter("debut", debutNom);
            query.setParameter("pays", paysFabriquant);

            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}