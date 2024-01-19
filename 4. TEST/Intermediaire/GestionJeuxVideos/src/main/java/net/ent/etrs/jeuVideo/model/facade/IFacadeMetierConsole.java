package net.ent.etrs.jeuVideo.model.facade;

import net.ent.etrs.jeuVideo.model.entities.Console;
import net.ent.etrs.jeuVideo.model.entities.JeuVideo;
import net.ent.etrs.jeuVideo.model.entities.references.Pays;
import net.ent.etrs.jeuVideo.model.facade.exceptions.BusinessException;

import java.util.List;
import java.util.Map;

public interface IFacadeMetierConsole {
    /**
     * Permet de sauvegarder une console
     * @param console la console à sauvegarder
     * @return la console sauvegarder
     */
    Console sauvegarderConsole(Console console) throws BusinessException;

    /**
     * Permet de récupérer toutes les consoles
     * @return la liste de toutes les console
     */
    List<Console> recupererlesConsoles() throws BusinessException;

    /**
     * Permet de récuperer la console qui est la plus vieille pour un pays
     * @param pays le pays choisi
     * @return la console la plus vieille pour le pays
     */
    Console recupererLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays(Pays pays) throws BusinessException;

    /**
     * Permet de récuperer un dictionnaire contenant toutes les consoles avec leur jeux video
     * @return une map contenant toutes les consoles avec leur jeux video
     */
    Map<Console,List<JeuVideo>> recupererConsoleAvecLeurJeuxVideo() throws BusinessException;

    /**
     * Permet de renvoyer une chaîne de caractère au format suivant : X années, Y mois, Z jours arrondi au jours près supérieur
     * donnant la durée moyenne entre 2 sortie de jeux vidéo pour une console
     * @param console la console choisie
     * @return une chaine de caractère qui donne la durée moyenne entre 2 sortie de jeux vidéo pour une console
     */
    String recupererDureeMoyenneSortieEntreJeuVideoPourUneConsole(Console console) throws BusinessException;
}