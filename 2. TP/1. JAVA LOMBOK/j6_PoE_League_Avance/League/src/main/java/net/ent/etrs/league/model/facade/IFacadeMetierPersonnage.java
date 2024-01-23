package net.ent.etrs.league.model.facade;

import net.ent.etrs.league.model.entities.Personnage;
import net.ent.etrs.league.model.facade.exceptions.BusinessException;

import java.util.Set;

public interface IFacadeMetierPersonnage {

    /**
     * Permet de sauvegarder un personnage
     *
     * @param personnage le personnage à sauvegarder
     */
    void savePersonnage(Personnage personnage) throws BusinessException;

    /**
     * Permet de supprimer un personnage
     *
     * @param personnage le personnage à supprimer
     */
    void deletePersonnage(Personnage personnage) throws BusinessException;

    /**
     * Permet de récupérer tous les personnages
     *
     * @return les personnages de la base
     */
    Set<Personnage> findAllPersonnages() throws BusinessException;
}