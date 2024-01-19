package net.ent.etrs.jeuVideo.model.facade;

import net.ent.etrs.jeuVideo.model.entities.Fabriquant;
import net.ent.etrs.jeuVideo.model.facade.exceptions.BusinessException;

import java.util.List;

public interface IFacadeMetierFabriquant {
    /**
     * Permet de sauvegarder un fabriquant
     * @param fabriquant le fabriquant à sauvegarder
     * @return le fabriquant sauvegarder
     */
    Fabriquant sauvegarderFabriquant(Fabriquant fabriquant) throws BusinessException;

    /**
     * Permet de récupérer tous les fabriquant
     * @return la liste de tous les fabriquant
     */
    List<Fabriquant> recupererlesFabriquants() throws BusinessException;
}