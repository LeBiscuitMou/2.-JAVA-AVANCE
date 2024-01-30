package net.ent.etrs.gestion_jeuvideo.models.dao;

import net.ent.etrs.gestion_jeuvideo.models.dao.exceptions.DaoException;
import net.ent.etrs.gestion_jeuvideo.models.entities.Console;
import net.ent.etrs.gestion_jeuvideo.models.entities.JeuVideo;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Genre;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Pays;
import net.ent.etrs.gestion_jeuvideo.models.exceptions.BusinessException;

import java.util.List;

public interface IDaoJeuVideo extends BaseDao<JeuVideo> {

    /**
     * Permet de récupérer tous les jeux video du genre choisi.
     *
     * @param genre le genre choisi
     * @return la liste des jeux video de ce genre
     * @throws DaoException l'exception lancée
     */
    List<JeuVideo> recupererJeuxVideoParGenre(Genre genre) throws DaoException;

    /**
     * Permet de récupérer tous les jeux video dont le nom commence par : et dont le fabricant est du pays sélectionné.
     *
     * @param debutNom       début du nom du jeu video
     * @param paysFabriquant pays du fabricant
     * @return la liste des jeux video répondant aux filtres
     * @throws DaoException l'exception lancée
     */
    List<JeuVideo> recupererJeuxVideoDontLeNomCommenceParEtLeFabriquantEstDeTelPays(String debutNom, Pays paysFabriquant) throws DaoException;

    /**
     * Permet de renvoyer la liste des jeux vidéos sortie sur la console choisie
     *
     * @param console la console choisie
     * @return une liste de jeu vidéo
     * @throws DaoException l'exception lancée
     */
    List<JeuVideo> recupererJeuVideoSortieSurConsole(Console console) throws DaoException;
}