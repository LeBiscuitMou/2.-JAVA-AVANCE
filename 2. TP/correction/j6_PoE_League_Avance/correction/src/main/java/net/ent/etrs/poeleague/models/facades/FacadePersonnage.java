package net.ent.etrs.poeleague.models.facades;

import net.ent.etrs.poeleague.models.entities.Personnage;
import net.ent.etrs.poeleague.models.facades.exception.BusinessException;

import java.util.Set;

public interface FacadePersonnage {

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
