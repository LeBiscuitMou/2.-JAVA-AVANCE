package net.ent.etrs.gestion_jeuvideo.models.facades.interfaces;

import net.ent.etrs.gestion_jeuvideo.models.entities.Console;
import net.ent.etrs.gestion_jeuvideo.models.entities.JeuVideo;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Genre;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Pays;
import net.ent.etrs.gestion_jeuvideo.models.exceptions.BusinessException;

import java.util.List;

public interface IFacadeJeuVideo extends GenericFacade<JeuVideo> {

    /**
     * Permet de sauvegarder un jeu video.
     *
     * @param jv le jeu video à sauvegarder
     * @return le jeu video sauvegarder
     * @throws BusinessException l'exception lancée
     */
    JeuVideo sauvergarderJeuVideo(JeuVideo jv) throws BusinessException;

    /**
     * Permet de récupérer tous les jeux video.
     *
     * @return la liste de tous les jeux video
     * @throws BusinessException l'exception lancée
     */
    List<JeuVideo> recupererJeuxVideo() throws BusinessException;

    /**
     * Permet de récupérer tous les jeux video du genre choisi.
     *
     * @param genre le genre choisi
     * @return la liste des jeux video de ce genre
     * @throws BusinessException l'exception lancée
     */
    List<JeuVideo> recupererJeuxVideoParGenre(Genre genre) throws BusinessException;

    /**
     * Permet de récupérer tous les jeux video dont le nom commence par : et dont le fabricant est du pays sélectionné.
     *
     * @param debutNom début du nom du jeu video
     * @param paysFabriquant pays du fabricant
     * @return la liste des jeux video répondant aux filtres
     * @throws BusinessException l'exception lancée
     */
    List<JeuVideo> recupererJeuxVideoDontLeNomCommenceParEtLeFabriquantEstDeTelPays(String debutNom, Pays paysFabriquant) throws BusinessException;

    String recupererDureeMoyenneSortieEntreJeuVideoPourUneConsole(Console console) throws BusinessException;
}
