package net.ent.etrs.gestion_jeuvideo.models.dao.impl;

import net.ent.etrs.gestion_jeuvideo.models.dao.IDaoJeuVideo;
import net.ent.etrs.gestion_jeuvideo.models.dao.exceptions.DaoException;
import net.ent.etrs.gestion_jeuvideo.models.entities.Console;
import net.ent.etrs.gestion_jeuvideo.models.entities.JeuVideo;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Genre;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Pays;

import javax.persistence.TypedQuery;
import java.util.List;

public class JeuVideoDaoJpaImpl extends JpaBaseDao<JeuVideo> implements IDaoJeuVideo {

    /**
     * Permet de récupérer tous les jeux video du genre choisi.
     *
     * @param genre le genre choisi
     * @return la liste des jeux video de ce genre
     * @throws DaoException l'exception lancée
     */
    @Override
    public List<JeuVideo> recupererJeuxVideoParGenre(Genre genre) throws DaoException {
        try {
            TypedQuery<JeuVideo> query = this.em.createQuery("""
                            SELECT jv
                            FROM JeuVideo jv
                            WHERE jv.genre = :genre"""
                    , JeuVideo.class);
            query.setParameter("genre", genre);

            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Permet de récupérer tous les jeux video dont le nom commence par : et dont le fabricant est du pays sélectionné.
     *
     * @param debutNom       début du nom du jeu video
     * @param paysFabriquant pays du fabricant
     * @return la liste des jeux video répondant aux filtres
     * @throws DaoException l'exception lancée
     */
    @Override
    public List<JeuVideo> recupererJeuxVideoDontLeNomCommenceParEtLeFabriquantEstDeTelPays(String debutNom, Pays paysFabriquant) throws DaoException {
        try {
            TypedQuery<JeuVideo> query = this.em.createQuery("""
                            SELECT jv
                            FROM JeuVideo jv LEFT JOIN jv.studioDev fab
                            WHERE jv.nom LIKE :debutNom
                                AND fab.pays = :paysFabriquant"""
                    , JeuVideo.class);
            query.setParameter("debutNom", debutNom +"%");
            query.setParameter("paysFabriquant", paysFabriquant);
            

        
            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Permet de renvoyer la liste des jeux vidéos sortie sur la console choisie
     *
     * @param console la console choisie
     * @return une liste de jeu vidéo
     * @throws DaoException l'exception lancée
     */
    @Override
    public List<JeuVideo> recupererJeuVideoSortieSurConsole(Console console) throws DaoException {
        try {
            TypedQuery<JeuVideo> query = this.em.createQuery("""
                            SELECT jv
                            FROM JeuVideo jv LEFT JOIN jv.plateformes co
                            WHERE co = :console"""
                    , JeuVideo.class);
            query.setParameter("console", console);

            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }
}