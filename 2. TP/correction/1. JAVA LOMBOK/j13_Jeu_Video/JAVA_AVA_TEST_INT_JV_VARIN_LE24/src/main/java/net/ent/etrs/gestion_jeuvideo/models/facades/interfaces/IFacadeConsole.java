package net.ent.etrs.gestion_jeuvideo.models.facades.interfaces;

import net.ent.etrs.gestion_jeuvideo.models.entities.Console;
import net.ent.etrs.gestion_jeuvideo.models.entities.JeuVideo;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Pays;
import net.ent.etrs.gestion_jeuvideo.models.exceptions.BusinessException;

import java.util.List;
import java.util.Map;

public interface IFacadeConsole extends GenericFacade<Console> {
    /**
     * Permet de sauvegarder une console.
     *
     * @param console la console à sauvegarder
     * @return la console sauvegarder
     * @throws BusinessException l'exception lancée
     */
    Console sauvegarderConsole(Console console) throws BusinessException;

    /**
     * Permet de récupérer toutes les consoles.
     *
     * @return la liste de toutes les console
     * @throws BusinessException l'exception lancée
     */
    List<Console> recupererlesConsoles() throws BusinessException;

    /**
     * Permet de récupérer la console qui est la plus vieille pour un pays.
     *
     * @param pays le pays choisi
     * @return la console la plus vieille pour le pays
     * @throws BusinessException l'exception lancée
     */
    Console recupererLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays(Pays pays) throws BusinessException;

    /**
     * Permet de récupérer un dictionnaire contenant toutes les consoles avec leurs jeux video.
     *
     * @return une map contenant toutes les consoles avec leurs jeux video
     * @throws BusinessException l'exception lancée
     */
    Map<Console,List<JeuVideo>> recupererConsoleAvecLeurJeuxVideo() throws BusinessException;
}
