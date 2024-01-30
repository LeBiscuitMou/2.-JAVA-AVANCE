package net.ent.etrs.gestion_jeuvideo.models.facades.interfaces;

import net.ent.etrs.gestion_jeuvideo.models.entities.Fabriquant;
import net.ent.etrs.gestion_jeuvideo.models.exceptions.BusinessException;

import java.util.List;

public interface IFacadeFabriquant extends GenericFacade<Fabriquant> {
    /**
     * Permet de sauvegarder un fabriquant.
     *
     * @param fabriquant le fabricant à sauvegarder
     * @return le fabricant sauvegarder
     * @throws BusinessException l'exception lancée
     */
    Fabriquant sauvegarderFabriquant(Fabriquant fabriquant) throws BusinessException;


    /**
     * Permet de récupérer tous les fabricants.
     *
     * @return la liste de tous les fabriquant
     * @throws BusinessException l'exception lancée
     */
    List<Fabriquant> recupererlesFabriquants() throws BusinessException;
}
