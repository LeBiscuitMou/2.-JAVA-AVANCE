package net.ent.etrs.league.model.facade;

import net.ent.etrs.league.model.entities.Personnage;

import java.util.Set;

public interface IFacadeMetierPersonnage {

    /**
     * Permet de sauvegarder un personnage
     *
     * @param personnage le personnage à sauvegarder
     */
    void savePersonnage(Personnage personnage);

    /**
     * Permet de supprimer un personnage
     *
     * @param personnage le personnage à supprimer
     */
    void deletePersonnage(Personnage personnage);

    /**
     * Permet de récupérer tous les personnages
     *
     * @return les personnages de la base
     */
    Set<Personnage> findAllPersonnages();
}