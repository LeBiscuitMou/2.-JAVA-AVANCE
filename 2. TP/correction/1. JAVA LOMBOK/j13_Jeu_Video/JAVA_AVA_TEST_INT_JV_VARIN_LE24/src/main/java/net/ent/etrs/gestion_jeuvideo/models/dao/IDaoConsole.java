package net.ent.etrs.gestion_jeuvideo.models.dao;

import net.ent.etrs.gestion_jeuvideo.models.dao.exceptions.DaoException;
import net.ent.etrs.gestion_jeuvideo.models.entities.Console;
import net.ent.etrs.gestion_jeuvideo.models.entities.JeuVideo;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Pays;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IDaoConsole extends BaseDao<Console> {

    /**
     * Permet de récupérer la console qui est la plus vieille pour un pays.
     *
     * @param pays le pays choisi
     * @return la console la plus vieille pour le pays
     * @throws DaoException l'exception lancée
     */
    Console recupererLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays(Pays pays) throws DaoException;

    /**
     * Permet de récupérer un dictionnaire contenant toutes les consoles avec leurs jeux video.
     *
     * @return une map contenant toutes les consoles avec leurs jeux video
     * @throws DaoException l'exception lancée
     */
    Map<Console, List<JeuVideo>> recupererConsoleAvecLeurJeuxVideo() throws DaoException;

    Optional<Console> findConsoleByIdentity(String nomConsole) throws DaoException;
}