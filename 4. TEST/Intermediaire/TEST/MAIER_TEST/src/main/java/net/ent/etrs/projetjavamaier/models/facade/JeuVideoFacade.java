package net.ent.etrs.projetjavamaier.models.facade;

import net.ent.etrs.projetjavamaier.models.entities.JeuVideo;
import net.ent.etrs.projetjavamaier.models.entities.references.Genre;
import net.ent.etrs.projetjavamaier.models.entities.references.Pays;
import net.ent.etrs.projetjavamaier.models.facade.base.FacadeMetier;
import net.ent.etrs.projetjavamaier.models.facade.exceptions.BusinessException;

import java.util.List;

public interface JeuVideoFacade extends FacadeMetier<JeuVideo> {
    List<JeuVideo> recupererJeuxVideoParGenre(Genre genre) throws BusinessException;

    /**
     * Permet de récuperer tous les jeux video dont le nom commence par : et dont le fabriquant est du pays selectionner
     * @param debutNom début du nom du jeu video
     * @param paysFabriquant pays du fabriquant
     * @return la liste des jeux video répondant aux filtres
     */
    List<JeuVideo> recupererJeuxVideoDontLeNomCommenceParEtLeFabriquantEstDeTelPays(String debutNom, Pays paysFabriquant) throws BusinessException;
}