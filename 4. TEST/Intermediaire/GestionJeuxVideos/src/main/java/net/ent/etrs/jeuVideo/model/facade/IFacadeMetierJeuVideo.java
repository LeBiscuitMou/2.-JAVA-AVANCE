package net.ent.etrs.jeuVideo.model.facade;

import net.ent.etrs.jeuVideo.model.entities.JeuVideo;
import net.ent.etrs.jeuVideo.model.entities.references.Genre;
import net.ent.etrs.jeuVideo.model.entities.references.Pays;
import net.ent.etrs.jeuVideo.model.facade.exceptions.BusinessException;

import java.util.List;

public interface IFacadeMetierJeuVideo {
    /**
     * Permet de sauvegarder un jeu video
     * @param jv le jeu vidfeo à sauvegarder
     * @return le jeu video sauvegarder
     */
    JeuVideo sauvegarderJeuVideo(JeuVideo jv) throws BusinessException;

    /**
     * Permet de récuperer tous les jeux video
     * @return la liste de tous les jeux video
     */
    List<JeuVideo> recupererJeuxVideo() throws BusinessException;

    /**
     * Permet de récuperer tous les jeux video du genre choisi
     * @param genre le genre choisi
     * @return la liste des jeux video de ce genre
     */
    List<JeuVideo> recupererJeuxVideoParGenre(Genre genre) throws BusinessException;

    /**
     * Permet de récuperer tous les jeux video dont le nom commence par : et dont le fabriquant est du pays selectionner
     * @param debutNom début du nom du jeu video
     * @param paysFabriquant pays du fabriquant
     * @return la liste des jeux video répondant aux filtres
     */
    List<JeuVideo> recupererJeuxVideoDontLeNomCommenceParEtLeFabriquantEstDeTelPays(String debutNom, Pays paysFabriquant) throws BusinessException;
}